package at.ac.univie.hci.btctracker.Data;

import java.io.Serializable;

/**
 * Created by rabizo on 23.04.2018.
 * Class for all Instructions in the App and Information about Currencies. Should be extended to more currencies
 */

public class Information {
    private String instruction;
    private String BitcoinInformation;
    private String EthereumInformation;
    private String RippleInformation;


    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getBitcoinInformation() {
        return BitcoinInformation;
    }

    public void setBitcoinInformation(String bitcoinInformation) {
        BitcoinInformation = bitcoinInformation;
    }

    public String getEthereumInformation() {
        return EthereumInformation;
    }

    public void setEthereumInformation(String ethereumInformation) {
        EthereumInformation = ethereumInformation;
    }

    public String getRippleInformation() {
        return RippleInformation;
    }

    public void setRippleInformation(String rippleInformation) {
        RippleInformation = rippleInformation;
    }


}
