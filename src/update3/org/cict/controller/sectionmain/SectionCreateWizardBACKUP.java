/**
 * CAPSTONE PROJECT.
 * BSIT 4A-G1.
 * MONOSYNC TECHNOLOGIES.
 * MONOSYNC FRAMEWORK VERSION 1.0.0 TEACUP RICE ROLL.
 * THIS PROJECT IS PROPRIETARY AND CONFIDENTIAL ANY PART THEREOF.
 * COPYING AND DISTRIBUTION WITHOUT PERMISSION ARE NOT ALLOWED.
 *
 * COLLEGE OF INFORMATION AND COMMUNICATIONS TECHNOLOGY.
 * LINKED SYSTEM.
 *
 * PROJECT MANAGER: JHON MELVIN N. PERELLO
 * DEVELOPERS:
 * JOEMAR N. DELA CRUZ
 * GRETHEL EINSTEIN BERNARDINO
 *
 * OTHER LIBRARIES THAT ARE USED BELONGS TO THEIR RESPECTFUL OWNERS AND AUTHORS.
 * NO COPYRIGHT ARE INTENTIONAL OR INTENDED.
 * THIS PROJECT IS NOT PROFITABLE HENCE FOR EDUCATIONAL PURPOSES ONLY.
 * THIS PROJECT IS ONLY FOR COMPLIANCE TO OUR REQUIREMENTS.
 * THIS PROJECT DOES NOT INCLUDE DISTRIBUTION FOR OTHER PURPOSES.
 *
 */
package update3.org.cict.controller.sectionmain;

import app.lazy.models.AcademicProgramMapping;
import app.lazy.models.AcademicTermMapping;
import app.lazy.models.CurriculumMapping;
import app.lazy.models.CurriculumSubjectMapping;
import app.lazy.models.DB;
import app.lazy.models.Database;
import app.lazy.models.LoadGroupMapping;
import app.lazy.models.LoadSectionMapping;
import app.lazy.models.MapFactory;
import app.lazy.models.SubjectMapping;
import update3.org.cict.SectionConstants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jhmvin.Mono;
import com.jhmvin.fx.async.Transaction;
import com.jhmvin.fx.async.TransactionException;
import com.jhmvin.fx.controls.MonoText;
import com.jhmvin.fx.display.ControllerFX;
import com.jhmvin.fx.display.LayoutDataFX;
import com.jhmvin.fx.display.SceneFX;
import com.jhmvin.propertymanager.FormFormat;
import com.jhmvin.transitions.Animate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.cict.SubjectClassification;
import org.cict.authentication.authenticator.CollegeFaculty;
import org.cict.authentication.authenticator.SystemProperties;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Jhon Melvin
 */
public class SectionCreateWizardBACKUP extends SceneFX implements ControllerFX {

    public void sout(Object message) {
        System.out.println(this.getClass().getSimpleName() + ": " + message.toString());
    }

    @FXML
    private VBox application_root;

    @FXML
    private JFXButton btn_back;

    @FXML
    private Label lbl_program_name;

    @FXML
    private Label lbl_curriculum_name;

    @FXML
    private Label lbl_curriculum_type;

    @FXML
    private VBox vbox_single;

    @FXML
    private Label lbl_single_term;

    @FXML
    private TextField txt_single_year_level;

    @FXML
    private TextField txt_single_section_name;

    @FXML
    private TextField txt_single_group_name;

    @FXML
    private TextField txt_single_adviser;

    @FXML
    private JFXButton btn_single_create;

    @FXML
    private JFXButton btn_single_back_main;

    @FXML
    private VBox vbox_main;

    @FXML
    private JFXButton btn_single_creation;

    @FXML
    private JFXButton btn_multi_creation;

    @FXML
    private VBox vbox_multi;

    @FXML
    private Label lbl_auto_term;

    @FXML
    private JFXCheckBox chk_first;

    @FXML
    private HBox hbox_first;

    @FXML
    private ComboBox<String> cmb_first_from;

    @FXML
    private ComboBox<String> cmb_first_to;

    @FXML
    private JFXCheckBox chk_second;

    @FXML
    private HBox hbox_second;

    @FXML
    private ComboBox<String> cmb_second_from;

    @FXML
    private ComboBox<String> cmb_second_to;

    @FXML
    private JFXCheckBox chk_second_ojt;

    @FXML
    private HBox hbox_second_ojt;

    @FXML
    private ComboBox<String> cmb_second_ojt_from;

    @FXML
    private ComboBox<String> cmb_second_ojt_to;

    @FXML
    private JFXCheckBox chk_third;

    @FXML
    private HBox hbox_third;

    @FXML
    private ComboBox<String> cmb_third_from;

    @FXML
    private ComboBox<String> cmb_third_to;

    @FXML
    private JFXCheckBox chk_fourth;

    @FXML
    private HBox hbox_fourth;

    @FXML
    private ComboBox<String> cmb_fourth_from;

    @FXML
    private ComboBox<String> cmb_fourth_to;

    @FXML
    private JFXCheckBox chk_fourth_ojt;

    @FXML
    private HBox hbox_fourth_ojt;

    @FXML
    private ComboBox<String> cmb_fourth_ojt_from;

    @FXML
    private ComboBox<String> cmb_fourth_ojt_to;

    @FXML
    private JFXButton btn_multi_create;

    @FXML
    private JFXButton btn_multi_back;

    @FXML
    private StackPane stack_multi;

    public SectionCreateWizardBACKUP() {
        //
    }

    private LayoutDataFX winRegularSectionControllerFx;
    private AcademicProgramMapping programMap;
    private CurriculumMapping curriculumMap;
    private String curriculumType;

    public void setWinRegularSectionControllerFx(LayoutDataFX winRegularSectionControllerFx) {
        this.winRegularSectionControllerFx = winRegularSectionControllerFx;
    }

    public void setProgramMap(AcademicProgramMapping programMap) {
        this.programMap = programMap;
    }

    public void setCurriculumMap(CurriculumMapping curriculumMap) {
        this.curriculumMap = curriculumMap;
    }

    public void setCurriculumType(String curriculumType) {
        this.curriculumType = curriculumType;
    }

    @Override
    public void onInitialization() {
        super.bindScene(application_root);
        /**
         * Default visibility.
         */
        this.vbox_main.setVisible(true);
        this.stack_multi.setVisible(false);
        this.vbox_single.setVisible(false);

        /**
         * set labels.
         */
        this.lbl_program_name.setText(programMap.getName());
        this.lbl_curriculum_name.setText(curriculumMap.getName());
        this.lbl_curriculum_type.setText(curriculumType);

        this.lbl_single_term.setText(SystemProperties.instance().getCurrentTermString());
        this.lbl_auto_term.textProperty().bind(this.lbl_single_term.textProperty());

        /**
         * Automatic creation, checks the curriculum type whether to disabled 1
         * and 2 or 3 and 4.
         */
        this.checkCurriculumType();
        /**
         * Single Creation Text Filter.
         */
        this.addTextFilters();

        this.resetControls();
        this.addCheckBoxListeners();
        this.addComboBoxListeners();
    }

    private void checkCurriculumType() {
        String type = curriculumMap.getLadderization_type();
        if (type.equalsIgnoreCase("NONE")) {
            // continous
        } else if (type.equalsIgnoreCase("PREPARATORY")) {
            // 1 and 2 only
            this.chk_third.setDisable(true);
            chk_fourth.setDisable(true);
        } else if (type.equalsIgnoreCase("PREPARATORY")) {
            //3 and 4 only
            this.chk_first.setDisable(true);
            this.chk_second.setDisable(true);
        }

    }

    private void addTextFilters() {
        /**
         * Filter Year Level.
         */
        FormFormat formFormatter = new FormFormat();

        FormFormat.IntegerFormat yearFilter = formFormatter.new IntegerFormat();
        yearFilter.setMinLimit(1);
        yearFilter.setMaxLimit(4);
        yearFilter.setFilterAction(filter -> {
            //System.out.println(filter.getFilterMessage());
            // do something when filter fails.
        });
        yearFilter.filter(this.txt_single_year_level.textProperty());

        /**
         * Filter Section Name.
         */
        FormFormat.CustomFormat sectionNameFilter = formFormatter.new CustomFormat();
        sectionNameFilter.setMaxCharacters(1);
        sectionNameFilter.setStringFilter(text -> {
            // custom filter
            if (StringUtils.isAlpha(text)) {
                sectionNameFilter.setMaxCharacters(1);
                return true;
            }
            if (StringUtils.isNumeric(text)) {
                if (text.equals("0")) {
                    return false;
                }
                sectionNameFilter.setMaxCharacters(2);
                return true;
            }
            return false;
        });
        sectionNameFilter.setFilterAction(filterAction -> {
            //System.out.println(filterAction.getFilterMessage());
            // do something when filter fails.
        });
        sectionNameFilter.filter(this.txt_single_section_name.textProperty());

        /**
         * Filter Group.
         */
        FormFormat.IntegerFormat groupFilter = formFormatter.new IntegerFormat();
        groupFilter.setMinLimit(1);
        groupFilter.setMaxLimit(2);
        groupFilter.setFilterAction(filter -> {
            //System.out.println(filter.getFilterMessage());
            // do something when filter fails.
        });
        groupFilter.filter(this.txt_single_group_name.textProperty());
    }

    @Override
    public void onEventHandling() {
        /**
         * Back to Section List.
         */
        super.addClickEvent(btn_back, () -> {
            Animate.fade(this.application_root, SectionConstants.FADE_SPEED, () -> {
                super.replaceRoot(winRegularSectionControllerFx.getApplicationRoot());

            }, winRegularSectionControllerFx.getApplicationRoot());
            // refresh section list.
            winRegularSectionControllerFx.
                    <WinRegularSectionsController>getController()
                    .fetchSections();

        });

        /**
         * Single creation.
         */
        super.addClickEvent(btn_single_creation, () -> {
            Animate.fade(vbox_main, SectionConstants.FADE_SPEED, () -> {
                vbox_main.setVisible(false);
                vbox_single.setVisible(true);
            }, vbox_single);
        });
        /**
         * single creation back to options.
         */
        super.addClickEvent(btn_single_back_main, () -> {
            Animate.fade(vbox_main, SectionConstants.FADE_SPEED, () -> {
                vbox_main.setVisible(true);
                vbox_single.setVisible(false);
            }, vbox_main);
        });

        /**
         * Automatic creation.
         */
        super.addClickEvent(btn_multi_creation, () -> {
            Animate.fade(vbox_main, SectionConstants.FADE_SPEED, () -> {
                stack_multi.setVisible(true);
                vbox_main.setVisible(false);
            }, stack_multi);
        });

        /**
         * Automatic Creation back to menu.
         */
        super.addClickEvent(btn_multi_back, () -> {
            Animate.fade(vbox_main, SectionConstants.FADE_SPEED, () -> {
                vbox_main.setVisible(true);
                stack_multi.setVisible(false);

            }, vbox_main);
        });

        /**
         * Create New Single Section.
         */
        super.addClickEvent(btn_single_create, () -> {
            this.createRegularSingle();
        });

        /**
         * Create Multiple Sections.
         */
        super.addClickEvent(btn_multi_create, () -> {
            createRegularMulti();
        });
    }

    private void resetControls() {
        /**
         * First Year Controls.
         */
        this.chk_first.setSelected(false);
        hbox_first.setDisable(true);

        /**
         * Second Years.
         */
        this.chk_second.setSelected(false);
        this.chk_second_ojt.setSelected(false);
        this.hbox_second.setDisable(true);
        this.hbox_second_ojt.setDisable(true);
        this.chk_second_ojt.setDisable(true);

        /**
         * Third Year
         */
        this.chk_third.setSelected(false);
        this.hbox_third.setDisable(true);
        /**
         * Fourth
         */
        this.chk_fourth.setSelected(false);
        this.chk_fourth_ojt.setSelected(false);
        this.chk_fourth_ojt.setDisable(true);
        this.hbox_fourth.setDisable(true);
        this.hbox_fourth_ojt.setDisable(true);

    }

    /**
     * Behavior of components dependent on the corresponding check box.
     */
    private void addCheckBoxListeners() {
        this.chk_first.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                enableFirstYear();
            } else {
                disableFirstYear();
            }
        });

        this.chk_second.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                enableSecondYear();
            } else {
                disableSecondYear();
            }
        });

        this.chk_second_ojt.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                enableSecondYearOjt();
            } else {
                disableSecondYearOjt();
            }
        });

        this.chk_third.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                enableThirdYear();
            } else {
                disableThirdYear();
            }
        });

        this.chk_fourth.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                enableFourthYear();
            } else {
                disableFourthYear();
            }
        });

        this.chk_fourth_ojt.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                enableFourthYearOjt();
            } else {
                disableFourthYearOjt();
            }
        });
    }

    // enables
    private void enableFirstYear() {
        hbox_first.setDisable(false);
    }

    private void enableSecondYear() {
        hbox_second.setDisable(false);
        this.chk_second_ojt.setDisable(false);
    }

    private void enableSecondYearOjt() {
        hbox_second_ojt.setDisable(false);
    }

    private void enableThirdYear() {
        hbox_third.setDisable(false);
    }

    private void enableFourthYear() {
        hbox_fourth.setDisable(false);
        this.chk_fourth_ojt.setDisable(false);
    }

    private void enableFourthYearOjt() {
        hbox_fourth_ojt.setDisable(false);
    }

    // disables
    private void disableFirstYear() {
        hbox_first.setDisable(true);
    }

    private void disableSecondYear() {
        hbox_second.setDisable(true);
        this.chk_second_ojt.setDisable(true);

        //
        this.chk_second_ojt.setSelected(false);
        this.chk_second_ojt.setDisable(true);
        hbox_second_ojt.setDisable(true);
    }

    private void disableSecondYearOjt() {
        hbox_second_ojt.setDisable(true);
    }

    //
    private void disableThirdYear() {
        hbox_third.setDisable(true);
    }

    private void disableFourthYear() {
        hbox_fourth.setDisable(true);
        this.chk_fourth_ojt.setDisable(true);

        //
        this.chk_fourth_ojt.setSelected(false);
        this.chk_fourth_ojt.setDisable(true);
        hbox_fourth_ojt.setDisable(true);
    }

    private void disableFourthYearOjt() {
        hbox_fourth_ojt.setDisable(true);
    }

    private void addComboBoxListeners() {
        // first years
        setComboBoxLimit(this.cmb_first_from, cmb_first_to, 0);

        // second years
        setComboBoxLimit(this.cmb_second_from, cmb_second_to, 0);
        // ojt
        setComboBoxLimit(cmb_second_to, cmb_second_ojt_from, 1, this.cmb_second_from);
        setComboBoxLimit(cmb_second_ojt_from, cmb_second_ojt_to, 1, this.cmb_second_from, this.cmb_second_to);

        // third year
        setComboBoxLimit(this.cmb_third_from, cmb_third_to, 0);

        //fourth
        setComboBoxLimit(this.cmb_fourth_from, cmb_fourth_to, 0);
        // ojt
        setComboBoxLimit(cmb_fourth_to, cmb_fourth_ojt_from, 1, this.cmb_fourth_from);
        setComboBoxLimit(cmb_fourth_ojt_from, cmb_fourth_ojt_to, 1, this.cmb_fourth_from, this.cmb_fourth_to);

    }

    /**
     * Set Limits to combo box.
     *
     * @param source
     * @param self
     * @param extra
     * @param padding
     */
    private void setComboBoxLimit(ComboBox<String> source, ComboBox<String> self, int extra, ComboBox<String>... padding) {

        addComboBoxContents(0, source);
        source.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            int selfStart = (int) newValue;

            for (ComboBox<String> comboBox : padding) {
                selfStart += comboBox.getSelectionModel().getSelectedIndex();
            }
            selfStart += extra;
            addComboBoxContents(selfStart, self);
            self.getSelectionModel().select(0);

        });
        source.getSelectionModel().select(0);

        if (padding.length != 0) {
            ComboBox refBox = padding[0];
            int storeIndex = refBox.getSelectionModel().getSelectedIndex();
            try {
                refBox.getSelectionModel().select(1);
            } catch (Exception e) {
                // list is only 1
            }
            refBox.getSelectionModel().select(storeIndex);
        }

        if (padding.length == 2) {

            source.getItems().addListener((ListChangeListener.Change<? extends String> c) -> {
                if (c.getList().isEmpty()) {
                    self.getItems().clear();
                }
            });

        }
    }

    /**
     * Load combo box values.
     *
     * @param index
     * @param comboBox
     */
    private void addComboBoxContents(int index, ComboBox<String> comboBox) {
        /**
         * Setup list set.
         */
        String charSet = "abcdefghijklmnopqrstuvxyz".toUpperCase(Locale.ENGLISH);
        ArrayList<String> charList = new ArrayList<>();
        for (Character c : charSet.toCharArray()) {
            charList.add(c.toString());
        }

        /**
         * Add to observable.
         */
        ObservableList<String> listModel = FXCollections.observableArrayList();
        for (int x = index; x < charSet.length(); x++) {
            try {
                listModel.add(charList.get(x));
            } catch (Exception e) {
                //
            }
        }
        comboBox.getItems().clear();
        comboBox.getItems().addAll(listModel);
    }

    /**
     * Inserts new single section in the database.
     */
    private void createRegularSingle() {
        if (this.txt_single_year_level.getText().trim().isEmpty()) {
            Mono.fx().snackbar().showError(this.application_root, "Please Assign a Year Level.");
            return;
        }
        if (this.txt_single_section_name.getText().trim().isEmpty()) {
            Mono.fx().snackbar().showError(this.application_root, "Please Assign a Section Name.");
            return;
        }
        if (this.txt_single_group_name.getText().trim().isEmpty()) {
            Mono.fx().snackbar().showError(this.application_root, "Please Assign a Section Group.");
            return;
        }

        int selected = Mono.fx().alert().createConfirmation()
                .setTitle("Confirm")
                .setHeader("Create New Section ?")
                .setMessage("Are you sure you want to add this section ?")
                .confirmYesNo();

        if (selected == 1) {
            CreateRegularSection createSingleTx = new CreateRegularSection();
            createSingleTx.secionGroup = MonoText.getInt(txt_single_group_name); //Integer.valueOf(this.txt_single_group_name.getText().trim().toUpperCase(Locale.ENGLISH));
            createSingleTx.yearLevel = MonoText.getInt(txt_single_year_level); //Integer.valueOf(this.txt_single_year_level.getText().trim().toUpperCase(Locale.ENGLISH));
            createSingleTx.sectionName = MonoText.getFormatted(txt_single_section_name); //this.txt_single_section_name.getText().trim().toUpperCase(Locale.ENGLISH);
            createSingleTx.curriculumMapping = this.curriculumMap;

            createSingleTx.whenStarted(() -> {
                btn_single_create.setDisable(true);
            });
            createSingleTx.whenCancelled(() -> {
                Mono.fx().snackbar().showError(this.application_root, "Section Already Exist.");
            });
            createSingleTx.whenFailed(() -> {
                Mono.fx().snackbar().showError(this.application_root, "Connection Error.");
            });
            createSingleTx.whenSuccess(() -> {
                Mono.fx().snackbar().showSuccess(this.application_root, "Section Successfully Added.");
                MonoText.clear(txt_single_group_name, txt_single_year_level, txt_single_section_name);
            });
            createSingleTx.whenFinished(() -> {
                btn_single_create.setDisable(false);
            });
            createSingleTx.transact();
        }
    }

    public void createRegularMulti() {
        CreateRegularSectionsAuto multiTx = new CreateRegularSectionsAuto();
        multiTx.curMap = this.curriculumMap;

        HashMap<Integer, Boolean> createList = new HashMap<>();
        createList.put(1, Boolean.TRUE);
        createList.put(2, Boolean.TRUE);
        createList.put(3, Boolean.TRUE);
        createList.put(4, Boolean.TRUE);

        SectionMeta sm1 = new SectionMeta();
        ArrayList<String> sn1 = new ArrayList<>();
        sn1.add("A");
        sn1.add("B");
        sn1.add("C");
        sm1.normalSections = sn1;

        SectionMeta sm2 = new SectionMeta();
        ArrayList<String> sn2 = new ArrayList<>();
        sn2.add("A");
        sn2.add("B");
        sn2.add("C");

        sm2.normalSections = sn2;

        SectionMeta sm3 = new SectionMeta();
        ArrayList<String> sn3 = new ArrayList<>();
        sn3.add("A");
        sn3.add("B");
        sn3.add("C");

        sm3.normalSections = sn3;

        SectionMeta sm4 = new SectionMeta();
        ArrayList<String> sn4 = new ArrayList<>();
        sn4.add("A");
        sn4.add("B");
        sn4.add("C");

        sm4.normalSections = sn4;

        ArrayList<String> sn4_ojt = new ArrayList<>();
        sn4_ojt.add("D");
        sn4_ojt.add("E");
        sn4_ojt.add("F");
        sm4.internSections = sn4_ojt;

        HashMap<Integer, SectionMeta> sectionNames = new HashMap<>();
        sectionNames.put(1, sm1);
        sectionNames.put(2, sm2);
        sectionNames.put(3, sm3);
        sectionNames.put(4, sm4);

        multiTx.yearsToCreate = createList;
        multiTx.sectionNames = sectionNames;

        multiTx.whenStarted(() -> {
            sout("Scheduled >>>>>>");
        });
        multiTx.whenCancelled(() -> {
            sout("CANCELED >>>>>>");
        });
        multiTx.whenFailed(() -> {
            sout("FUCKiNG FAILED");
        });
        multiTx.whenSuccess(() -> {
            sout("COMITTED SUCCESS");
        });
        multiTx.whenFinished(() -> {
            sout("DONE");
        });

        multiTx.transact();

    }

    //--------------------------------------------------------------------------
    private class CreateRegularSection extends Transaction {

        public String sectionName;
        public Integer yearLevel;
        public Integer secionGroup;
        public CurriculumMapping curriculumMapping;

        @Override
        protected boolean transaction() {
            AcademicTermMapping currentTerm = SystemProperties.instance().getCurrentAcademicTerm();
            /**
             * search if exist.
             */
            LoadSectionMapping exist = Mono.orm().newSearch(Database.connect().load_section())
                    .eq(DB.load_section().section_name, sectionName)
                    .eq((DB.load_section().year_level), yearLevel)
                    .eq((DB.load_section()._group), secionGroup)
                    .eq(DB.load_section().CURRICULUM_id, curriculumMapping.getId())
                    .eq(DB.load_section().ACADTERM_id, currentTerm.getId())
                    .eq(DB.load_section().type, SectionConstants.REGULAR)
                    .active()
                    .first();

            if (exist != null) {
                System.out.println(exist.getSection_name());
                sout("Section Existing");
                return false;
            }

            /**
             * When not existing proceed to creation.
             */
            LoadSectionMapping newLoadSection = MapFactory.map().load_section();
            newLoadSection.setACADTERM_id(currentTerm.getId());
            newLoadSection.setACADPROG_id(this.curriculumMapping.getACADPROG_id());
            newLoadSection.setCURRICULUM_id(this.curriculumMapping.getId());
            newLoadSection.setSection_name(sectionName);
            newLoadSection.setYear_level(yearLevel);
            newLoadSection.set_group(secionGroup);
            newLoadSection.setType(SectionConstants.REGULAR);
            newLoadSection.setCollege("CICT");
            newLoadSection.setCreated_date(Mono.orm().getServerTime().getDateWithFormat());
            newLoadSection.setCreated_by(CollegeFaculty.instance().getFACULTY_ID());

            Session localSession = Mono.orm().openSession();
            org.hibernate.Transaction dataTx = localSession.beginTransaction();

            /**
             * Insert section temporarily.
             */
            int temp_section_id = Database.connect()
                    .load_section()
                    .transactionalInsert(localSession, newLoadSection);

            if (temp_section_id <= 0) {
                /**
                 * Throw exception.
                 */
                dataTx.rollback();
                throw new TransactionException("load_section insertion error");
            }

            ArrayList<CurriculumSubjectMapping> subjects = Mono.orm().newSearch(Database.connect().curriculum_subject())
                    .eq(DB.curriculum_subject().CURRICULUM_id, curriculumMapping.getId())
                    .eq(DB.curriculum_subject().year, yearLevel)
                    .eq(DB.curriculum_subject().semester, currentTerm.getSemester_regular())
                    .active(Order.asc(DB.curriculum_subject().id))
                    .all();

            for (CurriculumSubjectMapping subject : subjects) {
                LoadGroupMapping loadGroup = MapFactory.map().load_group();
                loadGroup.setSUBJECT_id(subject.getSUBJECT_id());
                loadGroup.setLOADSEC_id(temp_section_id);
                loadGroup.setGroup_type("REGULAR");
                loadGroup.setAdded_date(Mono.orm().getServerTime().getDateWithFormat());
                loadGroup.setAdded_by(CollegeFaculty.instance().getFACULTY_ID());

                int temp_load_group = Database.connect().load_section()
                        .transactionalInsert(localSession, loadGroup);

                if (temp_load_group <= 0) {
                    /**
                     * Throw exception.
                     */
                    dataTx.rollback();
                    throw new TransactionException("load_group insertion error");
                }
            }

            /**
             * If ended properly.
             */
            dataTx.commit();
            return true;
        }

        @Override
        protected void after() {

        }
    }

    private class CreateRegularSectionsAuto extends Transaction {

        private void sout(Object message) {
            System.out.println(this.getClass().getSimpleName() + ": " + message.toString());
        }

        private HashMap<Integer, Boolean> yearsToCreate;
        private HashMap<Integer, SectionMeta> sectionNames;
        private CurriculumMapping curMap;

        @Override
        protected boolean transaction() {
            sout("transaction started");
            AcademicTermMapping currentTerm = SystemProperties
                    .instance()
                    .getCurrentAcademicTerm();
            /**
             * create local session.
             */
            Session localSession = Mono.orm().openSession();
            org.hibernate.Transaction dataTx = localSession.beginTransaction();

            sout("Session started");
            /**
             * Database partial transaction count;
             */
            int mutation_count = 0;
            for (Integer yearlevel = 1; yearlevel <= 4; yearlevel++) {
                /**
                 * If not included proceed to next year level.
                 */
                sout(" - - - - - - - - - - - - - - - - - - - - -");
                sout("Year Level: " + yearlevel);
                if (!yearsToCreate.getOrDefault(yearlevel, false)) {
                    sout("Not Included Skipping . . .");
                    continue;
                }

                /**
                 * Determine the OJT semester.
                 */
                Integer semester = currentTerm.getSemester_regular();
                Integer ojtSem = null;

                ArrayList<CurriculumSubjectMapping> curriculumSubjects = Mono.orm()
                        .newSearch(Database.connect().curriculum_subject())
                        .eq(DB.curriculum_subject().year, yearlevel)
                        .eq(DB.curriculum_subject().CURRICULUM_id, curMap.getId())
                        .active()
                        .all();

                /**
                 * Error Throw if the the curriculum has empty subjects.
                 */
                for (CurriculumSubjectMapping curSub : curriculumSubjects) {
                    SubjectMapping subject = Database.connect().subject()
                            .getPrimary(curSub.getSUBJECT_id());

                    if (subject.getType().equalsIgnoreCase(SubjectClassification.TYPE_INTERNSHIP)) {

                        if (curSub.getSemester().equals(1)) {
                            /**
                             * If OJT is in the first semester.
                             */
                            semester = 2;
                            ojtSem = 1;
                        } else if (curSub.getSemester().equals(2)) {
                            semester = 1;
                            ojtSem = 2;
                        }
                        break;
                    }
                }

                sout("Regular Sem: " + semester);
                sout("OJT Sem: " + String.valueOf(ojtSem));

                /**
                 * Section Creation.
                 */
                SectionMeta meta = sectionNames.get(yearlevel);

                ArrayList<String> normal = meta.normalSections;
                ArrayList<String> ojt = meta.internSections;

                /**
                 * Create Regular Sections. Throws Transaction error for failed
                 * insertion.
                 */
                int normal_mutation_count = 0;
                if (normal != null) {
                    for (String regNames : normal) {
                        for (Integer group = 1; group <= 2; group++) {
                            normal_mutation_count += insertSection(
                                    regNames,
                                    yearlevel,
                                    group,
                                    currentTerm,
                                    localSession,
                                    dataTx,
                                    semester);
                        }
                    }
                }

                /**
                 * Create OJT Sections. Throws Transaction error for failed
                 * insertion.
                 */
                int ojt_mutation_count = 0;
                if (ojt != null) {
                    /**
                     * If OJT names are existing.
                     */
                    if (ojtSem != null) {
                        /**
                         * If the curriculum has an ojt subject.
                         */
                        for (String regNames : ojt) {
                            for (Integer group = 1; group <= 2; group++) {
                                ojt_mutation_count += insertSection(
                                        regNames,
                                        yearlevel,
                                        group,
                                        currentTerm,
                                        localSession,
                                        dataTx,
                                        ojtSem);
                            }
                        }
                    }
                }

                mutation_count += (ojt_mutation_count + normal_mutation_count);
            }

            /**
             * commit changes if no errors were thrown.
             */
            dataTx.commit();
            localSession.close();
            return true;
        }

        /**
         * Insert section function.
         *
         * @param regNames
         * @param yearlevel
         * @param group
         * @param currentTerm
         * @param localSession
         * @param dataTx
         * @param semester
         */
        private int insertSection(
                // parameters.
                String regNames,
                Integer yearlevel,
                Integer group,
                AcademicTermMapping currentTerm,
                Session localSession,
                org.hibernate.Transaction dataTx,
                Integer semester) {
            /**
             * marker if this function has created a change in the session.
             */
            int mutated = 0;
            /**
             * search if exist.
             */
            LoadSectionMapping exist = Mono.orm().newSearch(Database.connect().load_section())
                    .eq(DB.load_section().section_name, regNames)
                    .eq((DB.load_section().year_level), yearlevel)
                    .eq((DB.load_section()._group), group)
                    .eq(DB.load_section().CURRICULUM_id, this.curMap.getId())
                    .eq(DB.load_section().ACADTERM_id, currentTerm.getId())
                    .eq(DB.load_section().type, SectionConstants.REGULAR)
                    .active()
                    .first();

            if (exist != null) {
                System.out.println(exist.getSection_name());
                sout("Section Existing");
                /**
                 * Skip this group.
                 */
                return mutated;
            }

            /**
             * When not existing proceed to creation.
             */
            LoadSectionMapping newLoadSection = MapFactory.map().load_section();
            newLoadSection.setACADTERM_id(currentTerm.getId());
            newLoadSection.setACADPROG_id(this.curMap.getACADPROG_id());
            newLoadSection.setCURRICULUM_id(this.curMap.getId());
            newLoadSection.setSection_name(regNames);
            newLoadSection.setYear_level(yearlevel);
            newLoadSection.set_group(group);
            newLoadSection.setType(SectionConstants.REGULAR);
            newLoadSection.setCollege("CICT");
            newLoadSection.setCreated_date(Mono.orm().getServerTime().getDateWithFormat());
            newLoadSection.setCreated_by(CollegeFaculty.instance().getFACULTY_ID());

            /**
             * Insert section temporarily.
             */
            int temp_section_id = Database.connect()
                    .load_section()
                    .transactionalInsert(localSession, newLoadSection);

            if (temp_section_id <= 0) {
                /**
                 * Throw exception.
                 */
                dataTx.rollback();
                /**
                 * make this transaction trigger fail call back.
                 */
                throw new TransactionException("load_section insertion error");
            }

            /**
             * Get Subjects from curriculum
             */
            ArrayList<CurriculumSubjectMapping> subjects = Mono.orm().newSearch(Database.connect().curriculum_subject())
                    .eq(DB.curriculum_subject().CURRICULUM_id, curMap.getId())
                    .eq(DB.curriculum_subject().year, yearlevel)
                    .eq(DB.curriculum_subject().semester, semester)
                    .active(Order.asc(DB.curriculum_subject().id))
                    .all();

            for (CurriculumSubjectMapping subject : subjects) {
                LoadGroupMapping loadGroup = MapFactory.map().load_group();
                loadGroup.setSUBJECT_id(subject.getSUBJECT_id());
                loadGroup.setLOADSEC_id(temp_section_id);
                loadGroup.setGroup_type("REGULAR");
                loadGroup.setAdded_date(Mono.orm().getServerTime().getDateWithFormat());
                loadGroup.setAdded_by(CollegeFaculty.instance().getFACULTY_ID());

                int temp_load_group = Database.connect().load_section()
                        .transactionalInsert(localSession, loadGroup);

                if (temp_load_group <= 0) {
                    /**
                     * Throw exception.
                     */
                    dataTx.rollback();
                    throw new TransactionException("load_group insertion error");
                }
            }

            /**
             * Something has changed in the session.
             */
            mutated = 1;
            return mutated;
        }

        @Override
        protected void after() {

        }

    }

    private class SectionMeta {

        private ArrayList<String> normalSections;
        private ArrayList<String> internSections;

    }

}