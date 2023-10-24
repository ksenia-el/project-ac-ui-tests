package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;


public class PredefinedStepDefsPartial {

    int iUserId;
    String sActivationCode;

    // ...

    @When("I retrieve info from DB for email {string}")
    public void iRetrieveInfoFromDbForEmail(String sEmailAddress) throws SQLException {
        System.out.print("Email: " + sEmailAddress + "\n");
        String sAccessToken = Helper.getAccessToken(sEmailAddress);
        // all data from response in array
        System.out.print("Data: " + sAccessToken + "\n\n");
        String[] sData = sAccessToken.split(";");
        System.out.println(sAccessToken);
        iUserId = Integer.parseInt(sData[0]);
        sActivationCode = sData[1];

        assertThat(sAccessToken).isNotEmpty();
    }


    @When("I activate user")
    public void iActivateUser() throws IOException {
        Helper.activateUser(iUserId, sActivationCode);
    }


    @Given("I open url {string}")
    public void iOpenUrl(String sUrl) {
        switch (sUrl) {
            case "title page":
                getDriver().get(XPathLibraryPartial.sStartPageURL);
                break;
            case "registration page":
                getDriver().get(XPathLibraryPartial.sRegistrationPage);
                break;
            default:
                System.out.println("The name of the page provided is not detected to be opened (could be either 'title page' or 'registration page'");
        }
    }

    @And("I click on button {string}")
    public void iClickOnButton(String sXpath) {
        switch (sXpath){
            case "Register Now":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sRegisterNowButton)).click();
                break;
            case "Register Me":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sRegisterMeButton)).click();
                break;
            case "Sign In":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sSignInButtonLoginPage)).click();
                break;
            case "Create New Quiz":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sCreateNewQuizButton)).click();
                break;
            case "Add Question":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sAddQuestionButton)).click();
                break;
            case "Save Quiz": // to save the quiz created
                getDriver().findElement(By.xpath(XPathLibraryPartial.sSaveQuizButton)).click();
                break;
            default:
                System.out.println("Name of the button provided is not detected, it should be either \"Register Now\", \"Register Me\", \"Sign In\", \"Create New Quiz\", \"Add Question\", \"Save Quiz\"");
        }

    }


    @When("I type {string} into field {string}")
    public void iTypeIntoField(String sWhatToType, String sInWhatField) {
        switch (sInWhatField){
            case "First Name":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sFirstNameInputField)).sendKeys(sWhatToType);
                break;
            case "Last Name":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sLastNameInputField)).sendKeys(sWhatToType);
                break;
            case "Email":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sEmailInputField)).sendKeys(sWhatToType);
                break;
            case "Group Code":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sGroupCodeInputField)).sendKeys(sWhatToType);
                break;
            case "Password":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sPasswordInputField)).sendKeys(sWhatToType);
                break;
            case "Confirm Password":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sConfirmPasswordInputField)).sendKeys(sWhatToType);
                break;
            case "Email *":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sEmailInputFieldLoginPage)).sendKeys(sWhatToType);
                break;
            case "Password *":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sPasswordInputFieldLoginPage)).sendKeys(sWhatToType);
                break;
            case "Title Of The Quiz *":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sTitleOfQuizInputField)).sendKeys(sWhatToType);
                break;

        }
    }

    @Then("element {string} should be displayed")
    public void elementShouldBeDisplayed(String sElementName) {
        switch (sElementName) {
            case "Register Me button":
                assertThat(getDriver().findElement(By.xpath(XPathLibraryPartial.sRegisterMeButton)).isDisplayed()).isTrue();
                break;
            case "Registration title":
                assertThat(getDriver().findElement(By.xpath(XPathLibraryPartial.sRegistrationTitleRegistrationPage)).isDisplayed()).isTrue();
                break;
            case "You have been Registered title":
                assertThat(getDriver().findElement(By.xpath(XPathLibraryPartial.sYouHaveBeenRegisteredTitleRegistrationPage)).isDisplayed()).isTrue();
                break;
            case "Back to Login page button":
                assertThat(getDriver().findElement(By.xpath(XPathLibraryPartial.sBackToLoginPageButton)).isDisplayed()).isTrue();
                break;
            case "My Grades left menu option":
                assertThat(getDriver().findElement(By.xpath(XPathLibraryPartial.sMyGradesLeftMenuOption)).isDisplayed()).isTrue();
                break;
            case "Quizzes left menu option":
                assertThat(getDriver().findElement(By.xpath(XPathLibraryPartial.sQuizzesLeftMenuOption)).isDisplayed()).isTrue();
                break;

        }
    }


    @Then("I select option {string} in the left menu")
    public void iSelectOptionInTheLeftMenu(String optionName) {
        switch(optionName) {
            case "Quizzes":
                getDriver().findElement(By.xpath(XPathLibraryPartial.sQuizzesLeftMenuOption)).click();
                break;

        }
    }

    //...
}