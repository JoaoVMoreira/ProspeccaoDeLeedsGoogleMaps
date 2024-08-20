package br.com.moreira.googleMapsLeeds.DTO;

import io.nayuki.qrcodegen.QrCode;

public class QrcodeViewDTO {
    private boolean success;
    private QrCode qrCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public QrCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QrCode qrCode) {
        this.qrCode = qrCode;
    }

    public QrcodeViewDTO(boolean success, QrCode qrCode){
        this.qrCode = qrCode;
        this.success = success;
    }
}
