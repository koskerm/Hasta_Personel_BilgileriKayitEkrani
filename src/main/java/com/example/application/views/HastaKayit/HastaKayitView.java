package com.example.application.views.HastaKayit;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.Controller.HastaController;
import com.example.application.Model.Hasta;
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

@PageTitle("Hasta Kayıt Ekranı")
@Route(value = "", layout = MainLayout.class)
public class HastaKayitView extends HorizontalLayout {
    @Autowired
    private HastaController hastaController;

    private TextField hastaTc;
    private TextField hastaAd;
    private TextField hastaSoyad;
    private TextField hastaTelefon;
    

    private EmailField hastaEmail;
    private TextArea hastaAdres;
    private ComboBox<String> hastaKanGrups;
    private ComboBox<String> cinsiyetComboBox ;

    private Button hastaSaveButton;
    private Button hastaCancelButton;

    private Hasta hasta = new Hasta();

    private Grid<Hasta> hastaGrid = new Grid<>(Hasta.class, false);
    private List<Hasta> hastaList;
    private Binder<Hasta> hastaBinder;

    private String[] kanGrups = { "0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+" };

    private HorizontalLayout hastaKayitMainlayout = new HorizontalLayout();
    private VerticalLayout leftVerticalLayout = new VerticalLayout();
    private VerticalLayout righVerticalLayout = new VerticalLayout();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    @PostConstruct
    public void init() {
        this.setSizeFull();
        add(buildHastaKayitMainLayout(), buildHastaGrid());
        initHastaBinder();
    }

    private HorizontalLayout buildHastaKayitMainLayout() {
        hastaTc = new TextField("Hasta TC");
        hastaTc.setMaxLength(11);
        hastaTc.setValueChangeMode(ValueChangeMode.EAGER);

    

        hastaAd = new TextField("Hasta Adı");
        hastaSoyad = new TextField("Hasta Soyadı");
        hastaTelefon = new TextField("Hasta Telefon");
        hastaTelefon.setMaxLength(11);
        hastaEmail = new EmailField("Hasta Email");
        hastaAdres = new TextArea("Hasta Adres");
        hastaKanGrups = new ComboBox<>();
        hastaKanGrups.setItems(kanGrups);
        hastaKanGrups.setPlaceholder("Hasta Kan Grubu");
        int count = 150;
        hastaAdres.setMaxLength(count);
        hastaAdres.setValueChangeMode(ValueChangeMode.EAGER);

        hastaAdres.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + count);
        });
        cinsiyetComboBox = new ComboBox<>("Cinsiyet", List.of("Erkek", "Kadın", "Diğer"));

            

        leftVerticalLayout.add(hastaTc,  hastaAd, hastaSoyad, hastaTelefon, buildButtonsLayout());
        leftVerticalLayout.getStyle().set("padding", "5%");

        righVerticalLayout.add(hastaEmail, hastaAdres, hastaKanGrups, cinsiyetComboBox);
        righVerticalLayout.getStyle().set("padding", "5%");

        hastaKayitMainlayout.add(leftVerticalLayout, righVerticalLayout);
        hastaKayitMainlayout.getStyle().set("width", "40%");
        return hastaKayitMainlayout;
    }

    private HorizontalLayout buildButtonsLayout() {
        hastaSaveButton = new Button("Kaydet");
        hastaSaveButton.addClickListener(e -> {
            validateAndSaveHasta();
        });

        hastaCancelButton = new Button("İptal");
        hastaCancelButton.addClickListener(e -> {

        hastaTc.setValue("");
        hastaAd.setValue("");
        hastaSoyad.setValue("");
        hastaTelefon.setValue("");
        hastaEmail.setValue("");
        hastaAdres.setValue("");
        hastaKanGrups.setValue("");
        cinsiyetComboBox.setValue("");


        });

        buttonsLayout.add(hastaSaveButton, hastaCancelButton);

        return buttonsLayout;
    }

    
    private Grid<Hasta> buildHastaGrid() {
        hastaList = hastaController.findAllHasta();
        hastaGrid.setItems(hastaList);
        hastaGrid.addColumn(Hasta::getHastaTc).setHeader("Hasta TC").setFrozen(true);
        hastaGrid.addColumn(Hasta::getHastaAd).setHeader("Hasta Adı").setFrozen(true);
        hastaGrid.addColumn(Hasta::getHastaSoyad).setHeader("Hasta Soyadı");
        hastaGrid.addColumn(Hasta::getHastaTel).setHeader("Hasta Telefon");
        hastaGrid.addColumn(Hasta::getHastaEmail).setHeader("Hasta Email");
        hastaGrid.addColumn(Hasta::getHastaAdres).setHeader("Hasta Adres");
        hastaGrid.addColumn(Hasta::getHastaKan).setHeader("Hasta Kan Grubu");
        hastaGrid.addColumn(Hasta::getCinsiyet).setHeader("Cinsiyet");

        hastaGrid.addColumn(new ComponentRenderer<>(Button::new, (button, h) -> {
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
            
            
            
            button.addClickListener(e -> {
                this.deleteHasta(h.getHastaTc());
                hastaList = hastaController.findAllHasta();
                hastaGrid.setItems(hastaList);});



            button.setIcon(new Icon(VaadinIcon.TRASH));
        })).setHeader("Manage").setFrozenToEnd(true);

        hastaGrid.setSelectionMode(SelectionMode.SINGLE);
        hastaGrid.getColumns().forEach(column -> column.setAutoWidth(true));

        hastaGrid.getStyle().set("width", "60%");

        hastaGrid.getColumns().forEach( c -> c.setSortable(true));
        return hastaGrid;
    }

    private StringToLongConverter hastaTCConverter() {
        StringToLongConverter idConverter = new StringToLongConverter("Lütfen sayısal bir değer giriniz.") {
            protected java.text.NumberFormat getFormat(Locale Id) {
                NumberFormat format = super.getFormat(Id);
                format.setGroupingUsed(false);
                return format;
            }
        };
        return idConverter;
    }

    private void initHastaBinder() {
        
        hastaBinder = new BeanValidationBinder<>(Hasta.class);
    
        hastaBinder.forField(hastaTc).withConverter(hastaTCConverter()).asRequired("Hasta TC alanı boş olamaz.")
                .bind(Hasta::getHastaTc, Hasta::setHastaTc);
        hastaBinder.forField(hastaAd).asRequired("Hasta adı boş olamaz").bind(Hasta::getHastaAd, Hasta::setHastaAd);
        hastaBinder.forField(hastaSoyad).asRequired("Hasta soyadı boş olamaz.").bind(Hasta::getHastaSoyad,
                Hasta::setHastaSoyad);
        hastaBinder.forField(hastaAdres).asRequired("Hasta adresi boş olamaz").bind(Hasta::getHastaAdres,
                Hasta::setHastaAdres);
        hastaBinder.forField(hastaTelefon).asRequired("Hasta Telefon alanı boş olamaz").bind(Hasta::getHastaTel,
                Hasta::setHastaTel);
        hastaBinder.forField(hastaEmail).asRequired("Hasta email alanı boş olamaz.").bind(Hasta::getHastaEmail,
                Hasta::setHastaEmail);
        hastaBinder.forField(hastaKanGrups).asRequired("Hasta kan grubunu seçiniz").bind(Hasta::getHastaKan,
                Hasta::setHastaKan);
        hastaBinder.forField(cinsiyetComboBox).asRequired("Cinsiyet seçiniz").bind(Hasta::getCinsiyet,
                Hasta::setCinsiyet);
    }

    private void validateAndSaveHasta() {
        try {
            hastaBinder.writeBean(hasta);
            hastaController.addHasta(hasta);
            hastaList = hastaController.findAllHasta();
            hastaGrid.setItems(hastaList);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR MESSAGE:" + e.getMessage());
        }
    }

    private void deleteHasta(Long Tc) {
        hastaController.deleteHasta(Tc);
    }
}
