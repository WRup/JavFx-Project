package Tests.TestFX;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.Test;
import org.testfx.api.FxRobotException;

import static org.testfx.api.FxAssert.verifyThat;

public class ControllersTests extends TextFxBase {

    final String SINGLE_AREA = "Strefa I \nMiasto Krakow";
    final String AGRO_AREA = "Strefa I + II \nAglomeracja";
    final String AMOUNT = "#ticketTextField";
    final String PRICE = "#labelPrice";
    final String REST = "#labelLeftPrice";
    final String NOTIFY = "#cautionText";
    final String PRINT_LABEL = "#printLabel";
    final String CANCEL = "#cancelButton";


    @Test(expected = FxRobotException.class)
    public void clickOnBoguElement(){
        clickOn("#asdkj");
    }

    @Test
    public void ensuereSingleAreaControllerDisplays()
    {
        clickOn(SINGLE_AREA);
    }
    @Test
    public void ensuereAgroAreaControllerDisplays()
    {
        clickOn(AGRO_AREA);
    }
    @Test
    public void ensureChosenTicketControllerDisplays()
    {
        ensuereSingleAreaControllerDisplays();
        clickOn("Normalny\n" + "2.80 zł");
    }
    @Test
    public void ensureChooseAmountControllerDisplays(){
        ensureChosenTicketControllerDisplays();
        clickOn("Wybierz ilość");
    }
    @Test
    public void ensureAmountLabelDisplaysProperly()
    {
        ensureChooseAmountControllerDisplays();
        String NUMBER = "1";
        clickOn(NUMBER);
        verifyThat(AMOUNT, (TextField textField) -> {
            String text = textField.getText();
            return text.contains(NUMBER);
        });
    }
    @Test
    public void ensurePaymentAreaControllerDisplays()
    {
        ensureChosenTicketControllerDisplays();
        clickOn("ZAPŁAĆ");
    }
    @Test
    public void ensurePayLabelDisplaysProperly()
    {
        ensuereSingleAreaControllerDisplays();
        clickOn("Ulgowy\n" + "2.50 zł");
        clickOn("ZAPŁAĆ");
        verifyThat(PRICE, (Label labelText) ->{
            String text = labelText.getText();
            return text.contains("2.50");
        });
    }
    @Test
    public void ensureRestLabelDisplayProperly()
    {
        ensuereSingleAreaControllerDisplays();
        clickOn("Ulgowy\n" + "2.50 zł");
        clickOn("ZAPŁAĆ");
        clickOn("50 gr");
        verifyThat(REST, (Label labelText) ->{
            String text = labelText.getText();
            return text.contains("2.00");
        });
    }
    @Test
    public void ensureNoChangeAvaliable()
    {
        ensurePaymentAreaControllerDisplays();
        clickOn("5 zł");
        verifyThat(NOTIFY, (Text textArea) ->{
            String text = textArea.getText();
            return text.contains("ODLICZONĄ");
                }
        );
    }
    @Test
    public void ensureGivesChange()
    {
        ensurePaymentAreaControllerDisplays();
        clickOn("2 zł");
        clickOn("50 gr");
        clickOn("50 gr");
        sleep(5001);
        verifyThat(PRINT_LABEL, (Label textArea) ->{
                    String text = textArea.getText();
                    return text.contains("resztę");
                }
        );
    }

    @Test
    public void ensureNoChangeNeeded()
    {
        ensurePaymentAreaControllerDisplays();
        clickOn("2 zł");
        clickOn("50 gr");
        clickOn("20 gr");
        clickOn("10 gr");
        sleep(5001);
        verifyThat(PRINT_LABEL, (Label textArea) ->{
                    String text = textArea.getText();
                    return text.contains("Odbierz bilety!");
                }
        );
    }

    @Test
    public void ensureCancelIsDisabled()
    {
        ensurePaymentAreaControllerDisplays();
        clickOn("2 zł");
        clickOn("50 gr");
        clickOn("20 gr");
        clickOn("10 gr");
        verifyThat(CANCEL, (Button cancelButton) ->{
            Boolean check = true;
            Boolean button = cancelButton.isDisabled();
                    return button.equals(check);
                }
        );
    }

}
