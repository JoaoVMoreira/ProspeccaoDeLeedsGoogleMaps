package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.controller.ControllerWhatsAppAPI;
import br.com.moreira.googleMapsLeeds.model.ComerciosModel;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import javafx.fxml.FXML;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceMainCommerce {
    private EntityManagerFactory factory;
    private ServiceTransitionCommerce serviceTransitionCommerce;
    private ControllerWhatsAppAPI controllerWhatsAppAPI = new ControllerWhatsAppAPI();
    public ServiceMainCommerce(EntityManagerFactory factory){
        this.factory = factory;
        this.serviceTransitionCommerce = new ServiceTransitionCommerce(factory);
    }
    private List<ComerciosModel> ListCommercesFromMainDB(){
        String query = "FROM comercioTransacao C";
        EntityManager em = factory.createEntityManager();
        try{
            List<ComerciosTransicaoModel> result = em.createQuery(query, ComerciosTransicaoModel.class).getResultList();
            return result.stream().map(c -> new ComerciosModel(c.getId(), c.getNome(), c.getSegmento(), c.getContato(), c.getSite(), c.getCttRealizado(), c.getCidade(), c.getPossuiWpp())).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            em.close();
        }
    }
    public List<ComerciosModel> ListMainCommerces(){
        String query = "FROM comercio C";
        EntityManager em = factory.createEntityManager();
        try{
            return em.createQuery(query, ComerciosModel.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            em.close();
        }
    }
    public void addCommercesInMainDB(){
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = null;
        List<ComerciosModel> data = ListCommercesFromMainDB();
        try{
            transaction = em.getTransaction();
            transaction.begin();
            for (ComerciosModel i : data){
                em.merge(i);
            }
            transaction.commit();
            serviceTransitionCommerce.DeleteAllFromTranditionDB();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            em.close();
        }
    }
    public void deleteItensFromMainDB(String id){
        EntityManager em = factory.createEntityManager();
        try{
            EntityTransaction transaction = em.getTransaction();
            ComerciosModel comercio = em.find(ComerciosModel.class, id);
            transaction.begin();
            em.remove(comercio);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            em.close();
        }
    }
    public void exportExcel() throws IOException {
        List<ComerciosModel> commerces = ListMainCommerces();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Infos Comercios");
        String sistemUsername = System.getProperty("user.name");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nome");
        header.createCell(1).setCellValue("Cidade");
        header.createCell(2).setCellValue("Contato Realziado?");
        header.createCell(3).setCellValue("Segmento");
        header.createCell(4).setCellValue("Site");
        header.createCell(5).setCellValue("Contato");

        int count = 1;
        for(ComerciosModel commerce : commerces){
            Row row = sheet.createRow(count++);
            row.createCell(0).setCellValue(commerce.getNome());
            row.createCell(1).setCellValue(commerce.getCidade());
            row.createCell(2).setCellValue(commerce.getCttRealizado());
            row.createCell(3).setCellValue(commerce.getSegmento());
            row.createCell(4).setCellValue(commerce.getSite());
            row.createCell(5).setCellValue(commerce.getContato());
        }
        LocalDateTime today = LocalDateTime.now();
        String todarStr = today.getYear() + "" + today.getMonthValue() + today.getDayOfMonth() + today.getHour() + today.getMinute();
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\" + sistemUsername +"\\Documents\\comercios" +todarStr+".xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Fecha o Workbook
        workbook.close();

        System.out.println("EXPORTAÇÃO REALIZADA");
    }
    public List<ComerciosModel> FilterCommercesWhatsApp(List<ComerciosModel> commerces) throws IOException, InterruptedException {
        List<ComerciosModel> filtredCommerces = new ArrayList<>();
        for(ComerciosModel comercio : commerces){
            if(comercio.getContato() != null && comercio.getPossuiWpp()){
                filtredCommerces.add(comercio);
            }
        }
        return filtredCommerces;
    }
}
