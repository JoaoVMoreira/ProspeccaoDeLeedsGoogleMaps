package br.com.moreira.googleMapsLeeds.view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QrcodeViewController implements Initializable {
    @FXML
    public ImageView qrCodeImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image convertedString = convertStringToQrCode(ConfigViewController.qrCodeString);
            qrCodeImageView.setImage(convertedString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void closeModal(){
        if(ConfigViewController.QRcodeModalStage != null){
            ConfigViewController.QRcodeModalStage.close();
            ConfigViewController.QRcodeModalStage = null;
        }
    }

    private Image convertStringToQrCode(String qrCode) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitQrCode = qrCodeWriter.encode(qrCode, BarcodeFormat.QR_CODE, 500, 500);
        ByteArrayOutputStream pngQrCode = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitQrCode, "PNG", pngQrCode);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pngQrCode.toByteArray());
        return new Image(inputStream);
    }

}
