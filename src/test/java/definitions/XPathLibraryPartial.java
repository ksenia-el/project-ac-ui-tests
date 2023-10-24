package definitions;

public class XPathLibraryPartial {

    //URLs
    public static String sStartPageURL = "***change_me***";
    public static String sRegistrationPage = "***change_me***";


    //Login page
    public static String sRegisterNowButton = "//span[contains(text(), 'Register Now')]"; // to start registration
    public static String sEmailInputFieldLoginPage = "//input[@placeholder = 'Email *']";
    public static String sPasswordInputFieldLoginPage = "//input[@placeholder = 'Password *']";
    public static String sSignInButtonLoginPage = "//span[contains(text(), 'Sign In')]/ancestor::button";


    //Registration page
    public static String sFirstNameInputField = "//input[@placeholder = 'First Name']";
    public static String sLastNameInputField = "//input[@placeholder = 'Last Name']";
    public static String sEmailInputField = "//input[@placeholder = 'Email']";
    public static String sGroupCodeInputField = "//input[@placeholder = 'Group Code']";
    public static String sPasswordInputField = "//input[@placeholder = 'Password']";
    public static String sConfirmPasswordInputField = "//input[@placeholder = 'Confirm Password']";
    public static String sRegisterMeButton = "//span[contains(text(), 'Register Me')]"; //to submit registration form
    public static String sRegistrationTitleRegistrationPage = "//h4[contains(text(), 'Registration')]";
    public static String sYouHaveBeenRegisteredTitleRegistrationPage = "//h4[contains(text(), 'You have been Registered')]";
    public static String sBackToLoginPageButton = "//span[contains(text(), 'Back to Login Page')]/ancestor::button";


    //Left menu of the App - for Student accounts!
    public static String sMyGradesLeftMenuOption = "//h5[contains(text(), 'My Grades')]";


    //left menu of the App - for Teacher accounts!
    public static String sQuizzesLeftMenuOption = "//h5[contains(text(), 'Quizzes')]";


    //Quizzes page
    public static String sCreateNewQuizButton = "//span[contains(text(), 'Create New Quiz')]/ancestor::button";
    public static String sAllQuizzesInfo = "//mat-expansion-panel";
    public static String sQuizTitleText = ".//mat-panel-title"; // used to get title of the quiz (selected from all quizzes that were found by the locator above)
    public static String sQuizDescription = ".//mat-panel-description"; //used to get info about number of questions in quiz selected before
    public static String sQuizPreviewButton = ".//span[contains(text(), 'Preview')]"; //used to open preview mode of the quiz selected
    public static String sQuizEditButton = ".//span[contains(text(), 'Edit')]";
    public static String sQuizDeleteButton = ".//span[contains(text(), 'Delete')]";
    // the next locator is used to confirm delete quiz in dialog window
    public static String sQuizDeleteConfirmButton = "//mat-dialog-container//button//span[contains(text(), 'Delete')]";

    //Quizzes page - Preview mode of selected quiz
    public static String sQuizPreviewWindow = ".//ac-quiz-preview";
    public static String sCloseQuizPreviewWindowButton = "//ac-quiz-preview//span[contains(text(), 'Close')]/ancestor::button";

    public static String sAllQuestionsCards = "//ac-quiz-preview//mat-card"; // used to get all cards with question info from the window with quiz preview
    // the next locator used to verify that specific question is marked as Show_stopper (in the quiz preview)
    public static String sShowStopperMarkInQuestion = ".//p[contains(text(), 'Show-Stopper')]";
    public static String sAllShowStoppersShownInQuizPreview = "//ac-quiz-preview//mat-card//p[contains(text(), 'Show-Stopper')]";
    //the next locators used for verification that some specific question in quiz is of specific type
    //meaning, these tags should be present in the code of a specific Question card
    public static String sSingleChoiceQuestionTag = ".//ac-quiz-single-choice-question";
    public static String sMultipleChoiceQuestionTag = ".//ac-quiz-multiple-choice-question";
    public static String sTextualQuestionTag = ".//ac-quiz-textual-question";



    //Quizzes page - quiz creation form
    public static String sTitleOfQuizInputField = "//input[contains(@placeholder,'Title Of The Quiz')]  ";
    public static String sAddQuestionButton = "//mat-icon[contains(text(), 'add_circle')]//ancestor::button";
    public static String sAllQuestionTitles = "//mat-panel-title[contains(text(), 'Q')]";
    public static String sAllQuestionsExpansionPanels = "//mat-panel-title[contains(text(), 'Q')]//ancestor::mat-expansion-panel-header";
    // the next locator detects all question forms on the page (expanded and not)
    public static String sAllQuestionForms = "//ac-question-body-form";
    public static String sTypesRadioButton = ".//mat-radio-button";
    public static String sQuestionPriceSlider = ".//mat-slider[@aria-valuemax!=100]";
    public static String sAddOptionButton = ".//span[contains(text(), 'Add Option')]";
    public static String sQuestionTitleInputField = ".//textarea[@formcontrolname='question']";
    public static String sQuestionOptions = ".//textarea[@formcontrolname='option']";
    //the next locator is used to select correct answer in Single-Choice questions
    public static String sQuestionOptionRadioButtons = ".//div[@class='right']//mat-radio-group//mat-radio-button";
    //the next locator is to select correct answer in Multiple-Choice questions
    public static String sQuestionOptionCheckboxes = "//textarea[@formcontrolname='option']//ancestor::mat-form-field/../mat-checkbox";
    public static String sShowStopperCheckbox = ".//span[contains(text(), 'Show-Stopper')]";
    public static String sShowStopperCheckboxStatus = ".//span[contains(text(), 'Show-Stopper')]//ancestor::mat-checkbox//input";
    public static String sSaveQuizButton = "//div[@class='controls']//span[contains(text(), 'Save')]";

    //...
}
