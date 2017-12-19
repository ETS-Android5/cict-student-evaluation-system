package org.bsu.cict.templating;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jhon Melvin
 */
public class AdvisingTemplate {

    //--------------------------------------------------------------------------
    // Upper fields
    private final static String STUDENT_NO = "student_no";
    private final static String FULL_NAME = "full_name";
    private final static String COURSE = "course";
    private final static String DATE = "date";
    private final static String ACADEMIC_YEAR = "school_year";
    private final static String ACADEMIC_TERM = "term";
    private final static String CAMPUS = "campus";
    private final static String CHK_OLD = "chk_old";
    private final static String CHK_NEW = "chk_new";
    private final static String CHK_REGULAR = "chk_regular";
    private final static String CHK_IRREGULAR = "chk_irregular";
    //--------------------------------------------------------------------------
    // Table Fields
    private final static String DAT_CODES = "dat_codes";
    private final static String DAT_TITLE = "dat_title";
    private final static String DAT_SECTION = "dat_section";
    private final static String DAT_LEC = "dat_lec";
    private final static String DAT_LAB = "dat_lab";
    //--------------------------------------------------------------------------
    // Remarks Count
    private final static String SUBJECT_COUNT = "subject_count";
    private final static String LEC_UNITS = "lec_units";
    private final static String LAB_UNITS = "lab_units";
    private final static String ADVISER = "adviser";
    // date field in this section will be the same as with the upper section
    //--------------------------------------------------------------------------
    private final static String SECOND_COPY = "second_copy";
    //--------------------------------------------------------------------------
    // TEMPLATE SOURCE
    private final static String ADVISING_ACROFORM = "templates/ADVISING_SLIP_TEMPLATE.pdf";
    //--------------------------------------------------------------------------
    // fillable fields
    // upper
    private String studentNo;
    private String fullName;
    private String course;
    private String date;
    private String academicYear;
    private String academicTerm;
    private String campus;
    private String chkOld;
    private String chkNew;
    private String chkRegular;
    private String chkIrregular;
    //--------------------------------------------------------------------------
    private String tableCodes;
    private String tableTitle;
    private String tableSection;
    private String tableLab;
    private String tableLec;
    //--------------------------------------------------------------------------
    private String subjectCount;
    private String lecUnits;
    private String labUnits;
    private String adviser;
    private String secondCopy;

    //--------------------------------------------------------------------------
    public enum CheckType {
        OLD, NEW, REGULAR, IRREGULAR
    }

    private String chkOnValue = "Yes";
    private String chkOffValue = "Off";

    public void setChecked(CheckType checkType) {
        this.chkOld = chkOffValue;
        this.chkNew = chkOffValue;
        this.chkRegular = chkOffValue;
        this.chkIrregular = chkOffValue;
        switch (checkType) {
            case OLD:
                this.chkOld = chkOnValue;
                break;
            case NEW:
                this.chkNew = chkOnValue;
                break;
            case REGULAR:
                this.chkRegular = chkOnValue;
                break;
            case IRREGULAR:
                this.chkIrregular = chkOnValue;
                break;
            default:
            // do nothing
        }
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setAcademicTerm(String academicTerm) {
        this.academicTerm = academicTerm;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * Formats a list into a single line.
     *
     * @param codeList
     * @return
     */
    public static String formatList(ArrayList<String> codeList) {
        String temp = "";
        Iterator<String> codes = codeList.iterator();
        while (codes.hasNext()) {
            temp = temp.concat(codes.next());
            if (codes.hasNext()) {
                temp += "\n";
            }
        }
        return temp;
    }

    public void setTableCodes(ArrayList<String> codeList) {
        this.tableCodes = formatList(codeList);
    }

    public void setTableTitle(ArrayList<String> titleList) {
        this.tableTitle = formatList(titleList);
    }

    public void setTableSection(ArrayList<String> sectionList) {
        this.tableSection = formatList(sectionList);
    }

    public void setTableLab(ArrayList<String> labList) {
        this.tableLab = formatList(labList);
    }

    public void setTableLec(ArrayList<String> lecList) {
        this.tableLec = formatList(lecList);
    }

    public void setSubjectCount(String subjectCount) {
        this.subjectCount = subjectCount;
    }

    public void setLecUnits(String lecUnits) {
        this.lecUnits = lecUnits;
    }

    public void setLabUnits(String labUnits) {
        this.labUnits = labUnits;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public void setSecondCopy(boolean secondCopy) {
        this.secondCopy = secondCopy ? "SECOND COPY" : "";
    }

    //--------------------------------------------------------------------------
    private String savePath;

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    //--------------------------------------------------------------------------
    public void stampTemplate() throws DocumentException, IOException {
        PdfReader reader = new PdfReader(ADVISING_ACROFORM);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(this.savePath));
        AcroFields form = stamper.getAcroFields();
        //----------------------------------------------------------------------
        // check states
        String[] states = form.getAppearanceStates(CHK_NEW);
        if (states.length == 1) {
            this.chkOnValue = states[0];
        } else if (states.length == 2) {
            this.chkOnValue = states[0];
            this.chkOffValue = states[1];
        }
        //----------------------------------------------------------------------
        form.setField(STUDENT_NO, this.studentNo);
        form.setField(FULL_NAME, this.fullName);
        form.setField(COURSE, this.course);
        form.setField(DATE, this.date);
        form.setField(ACADEMIC_YEAR, this.academicYear);
        form.setField(ACADEMIC_TERM, this.academicTerm);
        form.setField(CAMPUS, this.campus);
        form.setField(CHK_OLD, this.chkOld);
        form.setField(CHK_NEW, this.chkNew);
        form.setField(CHK_REGULAR, this.chkRegular);
        form.setField(CHK_IRREGULAR, this.chkIrregular);
        //----------------------------------------------------------------------
        form.setField(DAT_CODES, this.tableCodes);
        form.setField(DAT_TITLE, this.tableTitle);
        form.setField(DAT_SECTION, this.tableSection);
        form.setField(DAT_LAB, this.tableLab);
        form.setField(DAT_LEC, this.tableLec);
        //----------------------------------------------------------------------
        form.setField(SUBJECT_COUNT, this.subjectCount);
        form.setField(LEC_UNITS, this.lecUnits);
        form.setField(LAB_UNITS, this.labUnits);
        form.setField(ADVISER, this.adviser);
        //----------------------------------------------------------------------
        form.setField(SECOND_COPY, this.secondCopy);
        //----------------------------------------------------------------------
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }

    public static void main(String... args) {
        try {
            // Sample Usage.
            AdvisingTemplate a = new AdvisingTemplate();
            a.setStudentNo("2014113844");
            a.setFullName("PERELLO, JHON MELVIN NIETO");
            a.setCourse("BS INFORMATION TECHNOLOGY");
            a.setDate("12/17/2017 08:16 PM");
            a.setAcademicYear("2017-2018");
            a.setAcademicTerm("2");
            a.setCampus("MAIN");
            a.setChecked(CheckType.REGULAR);
            //------------------------------------------------------------------
            ArrayList<String> codes = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>();
            ArrayList<String> sec = new ArrayList<>();
            ArrayList<String> lec = new ArrayList<>();
            ArrayList<String> lab = new ArrayList<>();
            for (int x = 0; x < 10; x++) {
                codes.add("MATH 113");
                // limit to 28 characters to dis avoid overflow.
                titles.add("POLITICS AND GOVERNANCE WIT");
                sec.add("BSIT 4H-G2");
                lec.add("2.0");
                lab.add("1.0");
            }
            a.setTableCodes(codes);
            a.setTableTitle(titles);
            a.setTableSection(sec);
            a.setTableLec(lec);
            a.setTableLab(lab);
            //------------------------------------------------------------------
            a.setSubjectCount("10");
            a.setLecUnits("30");
            a.setLabUnits("10");
            a.setAdviser("JUAN DELA CRUZ");
            //------------------------------------------------------------------
            a.setSecondCopy(false);
            //------------------------------------------------------------------
            a.setSavePath("C:\\Users\\Jhon Melvin\\Desktop\\asd.pdf");
            // stamp
            a.stampTemplate();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}