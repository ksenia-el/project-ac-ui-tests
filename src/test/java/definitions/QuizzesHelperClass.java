// helper-class with methods to select a specific quiz (created before) and verify its content

// before using any method from this class:
// 1) the Quizzes page should be opened
// 2) method 'iSelectQuizByTitle' should be run - to select (and expand) specific quiz (found by the title provided)

// other methods in the class:
// 3) iQuizNumOfQuestionsIsEqual - to check the number of questions shown for a specific quiz (in the list of all quizzes)
// 4) iOpenPreviewOfTheQuiz - to open preview of the quiz selected before (using "Preview" button)
// 5) iClosePreviewOfTheQuiz
// 6) iVerifyQuestionIsShowStopper - to verify that specific question in the quiz (that was opened in a preview mode) is marked as "show-stopper"
// 7) iOnlyOneQuestionWithShowStopperIsShown - to check that only one question in the quiz is marked as "show-stopper"
// 8) noQuestionWithShowStopperIsShown - to check that no question in the quiz selected is marked as "show-stopper"
// 9) iVerifyThatTypeOfQuestionIs - to verify that the type of specific question (selected by number provided) is either 'Single-Choice', 'Multiple-Choice' or 'Textual'
// 10) iDeleteQuizSelected - to delete quiz already selected before

// before using these methods just the Quizzes page should be opened:
// 11) quizWithTitleIsListed - to check that some quiz with a specific title is present in the list of all quizzes
// 12) quizWithTitleIsNotListed


package definitions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;



public class QuizzesHelperClass {
    WebElement quizNeededFound; // this element contains everything that belongs to a specific quiz:
    // its title, number of questions inside and (if expanded) - more detailed info + "Preview", "Edit" and "Delete" buttons

    @When("I select quiz by title {string}")
    public void iSelectQuizByTitle(String quizTitleToFind) throws InterruptedException {
        List<WebElement> allQuizzes = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuizzesInfo));

        Thread.sleep(5000);
        for (WebElement currQuiz : allQuizzes) {
            WebElement currQuizTitleElement = currQuiz.findElement(By.xpath(XPathLibraryPartial.sQuizTitleText));
            String currQuizTitleText = currQuizTitleElement.getText();

            if (currQuizTitleText.equals(quizTitleToFind)) {
                quizNeededFound = currQuiz;
                currQuiz.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    @Then("I verify that number of questions in the quiz is equal to {int}")
    public void iQuizNumOfQuestionsIsEqual(int numQuestionsExpected) {
        String quizDescription = quizNeededFound.findElement(By.xpath(XPathLibraryPartial.sQuizDescription)).getText();
        String[] textInDescription = quizDescription.split("");
        int numberShown = Integer.parseInt(textInDescription[0]);
        assertThat(numberShown).isEqualTo(numQuestionsExpected);
    }


    @When("I open preview window of the quiz")
    public void iOpenPreviewOfTheQuiz() {
        WebElement previewButton = quizNeededFound.findElement(By.xpath(XPathLibraryPartial.sQuizPreviewButton));
        previewButton.click();
    }


    @When("I close preview window of the quiz")
    public void iClosePreviewOfTheQuiz() {
        WebElement closeButton = getDriver().findElement(By.xpath(XPathLibraryPartial.sCloseQuizPreviewWindowButton));
        closeButton.click();
    }


    @Then("I verify that question {int} is marked as Show-Stopper")
    public void iVerifyQuestionIsShowStopper(int questionNum) throws InterruptedException {
        List<WebElement> allQuestionCardsShown = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuestionsCards));
        Thread.sleep(2000);
        WebElement questionNeeded = allQuestionCardsShown.get(questionNum - 1);
        assertThat(questionNeeded.findElement(By.xpath(XPathLibraryPartial.sShowStopperMarkInQuestion)).isDisplayed()).isTrue();
    }


    @Then("I verify only one question in quiz is marked as Show-Stopper")
    public void iOnlyOneQuestionWithShowStopperIsShown() throws InterruptedException {
        List<WebElement> allShowStoppersShown = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllShowStoppersShownInQuizPreview));
        assertThat(allShowStoppersShown.size()).isEqualTo(1);
    }


    @Then("I verify no question in quiz is marked as Show-Stopper")
    public void iNoQuestionWithShowStopperIsShown() throws InterruptedException {
        List<WebElement> allShowStoppersShown = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllShowStoppersShownInQuizPreview));
        assertThat(allShowStoppersShown.size()).isEqualTo(0);
    }


    @Then("I verify that type of question {int} is {string}")
    public void iVerifyThatTypeOfQuestionIs(int questionNum, String questionType) {
        List<WebElement> allQuestionsInQuiz = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuestionsCards));
        WebElement questionNeeded = allQuestionsInQuiz.get(questionNum - 1);

        List<WebElement> singleChoiceTag = questionNeeded.findElements(By.xpath(XPathLibraryPartial.sSingleChoiceQuestionTag));
        List<WebElement> multipleChoiceTag = questionNeeded.findElements(By.xpath(XPathLibraryPartial.sMultipleChoiceQuestionTag));
        List<WebElement> textualTag = questionNeeded.findElements(By.xpath(XPathLibraryPartial.sTextualQuestionTag));

        List<WebElement> radioButtonsFound = questionNeeded.findElements(By.xpath(".//input[@type='radio']"));
        List<WebElement> checkboxesFound = questionNeeded.findElements(By.xpath(".//input[@type='checkbox']"));
        List<WebElement> textAreasFound = questionNeeded.findElements(By.xpath(".//textarea"));

        switch (questionType) {
            case "Single-Choice":
                assertThat(singleChoiceTag.size()).isEqualTo(1);
                assertThat(radioButtonsFound.size()).isNotEqualTo(0);
                assertThat(checkboxesFound.size()).isEqualTo(0);
                assertThat(textAreasFound.size()).isEqualTo(0);
                break;

            case "Multiple-Choice":
                assertThat(multipleChoiceTag.size()).isEqualTo(1);
                assertThat(radioButtonsFound.size()).isEqualTo(0);
                assertThat(checkboxesFound.size()).isNotEqualTo(0);
                assertThat(textAreasFound.size()).isEqualTo(0);
                break;

            case "Textual":
                assertThat(textualTag.size()).isEqualTo(1);
                assertThat(radioButtonsFound.size()).isEqualTo(0);
                assertThat(checkboxesFound.size()).isEqualTo(0);
                assertThat(textAreasFound.size()).isNotEqualTo(0);
                break;

            default:
                System.out.print("Incorrect type of question was provided to compare with (should be either 'Single-Choice', 'Multiple-Choice', 'Textual'");
                break;
        }
    }


    // the method deletes quiz that was already selected (and so expanded)
    @When("I delete quiz selected")
    public void iDeleteQuizSelected() throws InterruptedException {
        WebElement deleteButton = quizNeededFound.findElement(By.xpath(XPathLibraryPartial.sQuizDeleteButton));
        deleteButton.click();
        Thread.sleep(2000);
        WebElement confirmDeleteButton = getDriver().findElement(By.xpath(XPathLibraryPartial.sQuizDeleteConfirmButton));
        confirmDeleteButton.click();
    }

    //to use this method Quizzes page itself should be opened
    @Then("I verify that quiz with title {string} is listed")
    public void quizWithTitleIsListed(String quizTitleToFind) throws InterruptedException {
        List<WebElement> allQuizzes = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuizzesInfo));
        Thread.sleep(5000);
        int isListed = 0;
        for (WebElement currQuiz : allQuizzes) {
            String currQuizTitleText = currQuiz.findElement(By.xpath(XPathLibraryPartial.sQuizTitleText)).getText();
            if (currQuizTitleText.equals(quizTitleToFind)) {
                isListed++;
            }
        }
        assertThat(isListed).isGreaterThan(0);
    }


    //the Quizzes page should be opened to use this method
    @Then("I verify that quiz with title {string} is not listed")
    public void quizWithTitleIsNotListed(String quizTitleToFind) throws InterruptedException {
        List<WebElement> allQuizzes = getDriver().findElements(By.xpath(XPathLibraryPartial.sAllQuizzesInfo));
        int isListed = 0;
        for (WebElement currQuiz : allQuizzes) {
            WebElement quizTitleElement = currQuiz.findElement(By.xpath(XPathLibraryPartial.sQuizTitleText));
            String currQuizTitleText = quizTitleElement.getText();
            if (currQuizTitleText.equals(quizTitleToFind)) {
                isListed++;
            }
        }
        assertThat(isListed).isEqualTo(0);
    }

    @When ("I start to edit quiz")
    public void editQuizSelected() throws InterruptedException {
        WebElement editButton = quizNeededFound.findElement(By.xpath(XPathLibraryPartial.sQuizEditButton));
        editButton.click();
        Thread.sleep(2000);
    }
}