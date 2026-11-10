<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.google.zxing.client.j2se.MatrixToImageWriter"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.google.zxing.BarcodeFormat"%>
<%@page import="com.google.zxing.common.BitMatrix"%>
<%@page import="com.google.zxing.qrcode.QRCodeWriter"%>
<%
    int ancho = 300;
    int largo = 300;
    
    QRCodeWriter qr  = new QRCodeWriter();
    BitMatrix matriz = qr.encode("https://www.unedl.edu.mx",
       BarcodeFormat.QR_CODE,
       ancho, largo);
    BufferedImage imagen = 
            MatrixToImageWriter.toBufferedImage(matriz);
    response.setContentType("image/png");
    OutputStream salida = response.getOutputStream();
    ImageIO.write(imagen, "PNG", salida);
    salida.flush();
    salida.close();
%>