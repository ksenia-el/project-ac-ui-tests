// helper-class with methods to create a new quiz

// before using any method from this class 2 main methods should be called firstly:
// 1) iSelectQuestionToSetup - to select (and expand it, if needed) a specific question by it's number
// 2) iSetQuestionTypeAs - to setup a type of question selected (it should be either 'Single-Choice', 'Multiple-Choice' or 'Textual')

// other methods provided:
// 3) iSetQuestionPrice - to setup the price of question selected (should be in range 1-10)
// 4) iAddOneMoreOption - to add one more option in the question selected
// 5) iSetQuestionTitle - to setup the title of the question selected
// 6) iSetOption - to setup option (specified by number and with the string provided) of the question selected
// 7) iSetOptionAsCorrectAnswer - to selected correct(s) answer in the question selected (one - for Single-Choice type, multiple - for Multiple-Choice)
// 8) iSetQuestionAsShowStopper - to select the question as Show-Stopper


package definitions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static support.TestContext.getDriver;
import org.openqa.selenium.Keys;
import static org.assertj.core.api.Assertions.assertThat;


public class QuestionsHelperClass {
    int questionNumForSetup;
    WebElement questionFormNeeded;
    String questionTypeSelected;

    @When("I select question number {int} for setup")
    public void iSelectQuestionToSetup(int questionNum) throws InterruptedException {
        questionNumForSetup = questionNum;
        System.out.println("Question num: " + questionNumForSetup);

        //in case the question is not expanded yet (to start setup)
        List<WebElement> allQuestionsExpansionPanels =  getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuestionsExpansionPanels));
        String expansionValue = allQuestionsExpansionPanels.get(questionNum - 1).getAttribute("aria-expanded");
        if (expansionValue.equals("false")) {
            allQuestionsExpansionPanels.get(questionNum - 1).click();
            Thread.sleep(2000);
        }

        // then get all elements from the form with settings for the specific question
        List<WebElement> allQuestionForms = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuestionForms));
        System.out.println("Question Forms detected: " + allQuestionForms.size());
        questionFormNeeded = allQuestionForms.get(questionNum - 1);
        System.out.println("Question form: " + questionFormNeeded);
        // ? should I add some assertion
    }


    @When("I set question type as {string}")
    public void iSetQuestionTypeAs(String questionType) {
        questionTypeSelected = questionType;

        List<WebElement> typesRadioButtons = questionFormNeeded.findElements(By.xpath(XPathLibraryPartial.sTypesRadioButton));
        System.out.println("Type Radio Buttons detected: " + typesRadioButtons.size());
        switch (questionType) {
            case "Textual":
                typesRadioButtons.get(0).click();
                break;
            case "Single-Choice":
                typesRadioButtons.get(1).click();
                break;
            case "Multiple-Choice":
                typesRadioButtons.get(2).click();
                break;
            default:
                System.out.println("The type was not selected (check the type provided, it should be either 'Single-Choice', 'Multiple-Choice' or 'Textual')");
        }
    }


    @When("I set question price as {int}") // could be only in range 1-10
    public void iSetQuestionPrice(int questionPrice){
        WebElement priceSlider = questionFormNeeded.findElement(By.xpath(XPathLibraryPartial.sQuestionPriceSlider));
        //as the slider is always at position 5 by default
        if (questionPrice > 5) {
            for (int i = 5; i < questionPrice; i++) {
                priceSlider.sendKeys(Keys.ARROW_RIGHT);
            }
        }
        if (questionPrice < 5) {
            for (int i = 5; i > questionPrice; i--) {
                priceSlider.sendKeys(Keys.ARROW_LEFT);
            }
        }
        if (questionPrice > 10 || questionPrice < 1) {
            System.out.println("The price of question provided is incorrect (should be in range 1-10, actual: " + questionPrice + ")");
        }
    }


    @When("I add one more option in the question selected")
    public void iAddOneMoreOption(){
        questionFormNeeded.findElement(By.xpath(XPathLibraryPartial.sAddOptionButton)).click();
    }


    @When("I set question title as {string}")
    public void iSetQuestionTitle(String questionTitle){
        WebElement quizTitleField = questionFormNeeded.findElement(By.xpath(XPathLibraryPartial.sQuestionTitleInputField));
        quizTitleField.click();
        quizTitleField.sendKeys(questionTitle);
    }


    @When("I set option {int} as {string}")
    public void iSetOption(int optionNum, String optionText){
        List<WebElement> allQuestionOptions = questionFormNeeded.findElements(By.xpath(XPathLibraryPartial.sQuestionOptions));
        WebElement optionNeeded = allQuestionOptions.get(optionNum-1);
        optionNeeded.click();
        optionNeeded.sendKeys(optionText);
    }


    @When("I set option {int} as correct answer")
    public void iSetOptionAsCorrectAnswer(int optionNum) {
        switch (questionTypeSelected){
            case "Single-Choice":
                List<WebElement> allOptionRadioButtons = questionFormNeeded.findElements(By.xpath(XPathLibraryPartial.sQuestionOptionRadioButtons));
                System.out.println("Option Radio Buttons (to select correct answer) detected: " + allOptionRadioButtons.size());
                WebElement optionRadioButtonNeeded = allOptionRadioButtons.get(optionNum-1);
                optionRadioButtonNeeded.click();
                break;
            case "Multiple-Choice":
                List<WebElement> allOptionCheckboxes = questionFormNeeded.findElements(By.xpath(XPathLibraryPartial.sQuestionOptionCheckboxes));
                System.out.println("Option Checkboxes (to select correct answer) detected: " + allOptionCheckboxes.size());
                WebElement optionCheckboxNeeded = allOptionCheckboxes.get(optionNum-1);
                optionCheckboxNeeded.click();
                break;
            default:
                System.out.println("Option was not set as a correct answer for the question (check the number of option provided;" +
                        "function works only for 'Single-Choice' and 'Multiple-Choice' types of questions, not for 'Textual'!");
        }
    }


    @When("I set question as show-stopper")
    public void iSetQuestionAsShowStopper() {
        questionFormNeeded.findElement(By.xpath(XPathLibraryPartial.sShowStopperCheckbox)).click();
        // ? should I add some assertion
    }

    @Then("I verify that question is checked as \"Show-Stopper\"")
    public void iVerifyQuestionIsCheckedAsShowStopper(){
        WebElement showStopperCheckbox = questionFormNeeded.findElement(By.xpath(XPathLibraryPartial.sShowStopperCheckboxStatus));
        String isChecked = showStopperCheckbox.getAttribute("aria-checked");
        assertThat(isChecked).isEqualTo("true");
    }

    @Then("I verify that question is not checked as \"Show-stopper\"")
    public void iVerifyQuestionIsNotCheckedAsShowStopper(){
        WebElement showStopperCheckbox = questionFormNeeded.findElement(By.xpath(XPathLibraryPartial.sShowStopperCheckboxStatus));
        String isChecked = showStopperCheckbox.getAttribute("aria-checked");
        assertThat(isChecked).isEqualTo("false");
    }

}

