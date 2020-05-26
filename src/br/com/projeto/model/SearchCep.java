package br.com.projeto.model;

import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SearchCep
{
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String Bairro) {
        this.bairro = Bairro;
    }

    public String getRua() {
        return rua;
    }

    // Properties
    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    private String logradouro;

    private int result = 0;
    private String resultText;

    public SearchCep(String cep)
    {
        try 
        {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");

            Document document = getDocumento(url);

            Element root = document.getRootElement();

            for (Iterator i = root.elementIterator(); i.hasNext();) 
            {
                Element element = (Element) i.next();

                if(element.getQualifiedName().equals("uf")) setEstado(element.getText());

                if(element.getQualifiedName().equals("cidade")) setCidade(element.getText());

                if(element.getQualifiedName().equals("bairro")) setBairro(element.getText());

                if(element.getQualifiedName().equals("logradouro")) setLogradouro(element.getText());
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public Document getDocumento(URL url) throws DocumentException 
    {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        return document;
    } 
}