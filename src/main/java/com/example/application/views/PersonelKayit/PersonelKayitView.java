package com.example.application.views.PersonelKayit;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.Controller.PersonelController;
import com.example.application.Model.Personel;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;

@PageTitle("Personel Kayıt Ekranı")
@Route(value = "personel-kayit", layout = MainLayout.class)
public class PersonelKayitView extends HorizontalLayout {
    @Autowired
    private PersonelController personelController;

    private TextField personelTc;
    private TextField personelAd;
    private TextField personelSoyad;
    private TextField personelTelefon;
    private EmailField personelEmail;
    private TextArea personelAdres;
    private ComboBox<String> personelKanGrup;

    private Button personelSaveButton;
    private Button personelCancelButton;

    private Personel personel = new Personel();

    private Grid<Personel> personelGrid = new Grid<>(Personel.class, false);
    private List<Personel> personelList;
    private Binder<Personel> personelBinder;

    private String[] kanGrups = { "0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+" };

    private HorizontalLayout personelKayitMainlayout = new HorizontalLayout();
    private VerticalLayout leftVerticalLayout = new VerticalLayout();
    private VerticalLayout righVerticalLayout = new VerticalLayout();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    @PostConstruct
    public void init() {
        this.setSizeFull();
        add(buildPersonelKayitMainLayout(), buildPersonelGrid());
        initPersonelBinder();
    }

    private HorizontalLayout buildPersonelKayitMainLayout() {
        personelTc = new TextField("Personel Tc");
        personelTc.setMaxLength(11);
        personelTc.setValueChangeMode(ValueChangeMode.EAGER);

        personelAd = new TextField("Personel Adı");
        personelSoyad = new TextField("Personel Soyadı");
        personelTelefon = new TextField("Personel Telefon");
        personelTelefon.setMaxLength(11);
        personelEmail = new EmailField("Personel Email");
        personelAdres = new TextArea("Personel Adres");
        personelKanGrup = new ComboBox<>();
        personelKanGrup.setItems(kanGrups);
        personelKanGrup.setPlaceholder("Personel Kan Grubu");
        int count = 150;
        personelAdres.setMaxLength(count);
        personelAdres.setValueChangeMode(ValueChangeMode.EAGER);
        personelAdres.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + count);
        });

        leftVerticalLayout.add(personelTc, personelAd, personelSoyad, personelTelefon, buildButtonsLayout());
        leftVerticalLayout.getStyle().set("padding", "5%");

        righVerticalLayout.add(personelEmail, personelAdres, personelKanGrup);
        righVerticalLayout.getStyle().set("padding", "5%");

        personelKayitMainlayout.add(leftVerticalLayout, righVerticalLayout);
        personelKayitMainlayout.getStyle().set("width", "40%");
        return personelKayitMainlayout;
    }

    private HorizontalLayout buildButtonsLayout() {
        personelSaveButton = new Button("Kaydet");
        personelSaveButton.addClickListener(e -> {
            validateAndSavePersonel();
        });

        personelCancelButton = new Button("İptal");
        personelCancelButton.addClickListener(e -> {
        personelTc.setValue("");
        personelAd.setValue("");
        personelSoyad.setValue("");
        personelTelefon.setValue("");
        personelEmail.setValue("");
        personelAdres.setValue("");
        personelKanGrup.setValue("");

        });

        buttonsLayout.add(personelSaveButton, personelCancelButton);

        return buttonsLayout;
    }

    
    private Grid<Personel> buildPersonelGrid() {
        personelList = personelController.findAllPersonel();
        personelGrid.setItems(personelList);
        personelGrid.addColumn(Personel::getPersonelTc).setHeader("Personel TC").setFrozen(true);
        personelGrid.addColumn(Personel::getPersonelAd).setHeader("Personel Adı").setFrozen(true);
        personelGrid.addColumn(Personel::getPersonelSoyad).setHeader("Personel Soyadı");
        personelGrid.addColumn(Personel::getPersonelTelefon).setHeader("Personel Telefon");
        personelGrid.addColumn(Personel::getPersonelEmail).setHeader("Personel Email");
        personelGrid.addColumn(Personel::getPersonelAdres).setHeader("Personel Adres");
        personelGrid.addColumn(Personel::getPersonelKanGrup).setHeader("Personel Kan Grubu");
        personelGrid.addColumn(new ComponentRenderer<>(Button::new, (button, h) -> {
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
            
                button.addClickListener(e -> {
                this.deletePersonel(h.getPersonelTc());
                personelList = personelController.findAllPersonel();
                personelGrid.setItems(personelList);});
            
            button.setIcon(new Icon(VaadinIcon.TRASH));
        })).setHeader("Manage").setFrozenToEnd(true);

        personelGrid.setSelectionMode(SelectionMode.SINGLE);
        personelGrid.getColumns().forEach(column -> column.setAutoWidth(true));

        personelGrid.getStyle().set("width", "60%");
        return personelGrid;
    }

    private StringToLongConverter personelTCConverter() {
        StringToLongConverter idConverter = new StringToLongConverter("Lütfen sayısal bir değer giriniz.") {
            protected java.text.NumberFormat getFormat(Locale Id) {
                NumberFormat format = super.getFormat(Id);
                format.setGroupingUsed(false);
                return format;
            }
        };
        return idConverter;
    }

    private void initPersonelBinder() {
        // Binder kullanıcının girmiş olduğu değerleri veritabanına erişim sağlamadan
        // ram üzerinde tutarak Hasta modelinin propert'lerine set eder.
        
        personelBinder = new BeanValidationBinder<>(Personel.class);
    
        personelBinder.forField(personelTc).withConverter(personelTCConverter()).asRequired("Personel TC alanı boş olamaz.")
                .bind(Personel::getPersonelTc, Personel::setPersonelTc);
        personelBinder.forField(personelAd).asRequired("Personel adı boş olamaz").bind(Personel::getPersonelAd, Personel::setPersonelAd);
        personelBinder.forField(personelSoyad).asRequired("Personel soyadı boş olamaz.").bind(Personel::getPersonelSoyad,
                Personel::setPersonelSoyad);
        personelBinder.forField(personelAdres).asRequired("Personel adresi boş olamaz").bind(Personel::getPersonelAdres,
                Personel::setPersonelAdres);
        personelBinder.forField(personelTelefon).asRequired("Personel Telefon alanı boş olamaz").bind(Personel::getPersonelTelefon,
                Personel::setPersonelTelefon);
        personelBinder.forField(personelEmail).asRequired("Personel email alanı boş olamaz.").bind(Personel::getPersonelEmail,
                Personel::setPersonelEmail);
        personelBinder.forField(personelKanGrup).asRequired("Personel kan grubunu seçiniz").bind(Personel::getPersonelKanGrup,
                Personel::setPersonelKanGrup);
    }

    private void validateAndSavePersonel() {
        try {
            System.out.println("PERSONEL VALIDATE AND SAVE");
            personelBinder.writeBean(personel);
            personelController.addPersonel(personel);
            personelList = personelController.findAllPersonel();
            personelGrid.setItems(personelList);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR MESSAGE:" + e.getMessage());
        }
    }

    private void deletePersonel(Long Tc) {
        personelController.deletePersonel(Tc);
    }
}
