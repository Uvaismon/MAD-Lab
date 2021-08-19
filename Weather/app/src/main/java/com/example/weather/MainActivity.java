package com.example.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    Button parsexmlbutton, parsejsonbutton;
    TextView resulttextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parsexmlbutton=findViewById(R.id.parsexmlbutton);
        parsejsonbutton=findViewById(R.id.parsejsonbutton);
        resulttextview=findViewById(R.id.resulttextView);

        parsexmlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream inputStream=getAssets().open("city.xml");
                    //parse xml
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(inputStream);

                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append("XML Data");
                    stringBuilder.append("\n..............");

                    NodeList nodeList=document.getElementsByTagName("place"); //search the tag name and find the number of similar tags available

                    for (int i=0; i<nodeList.getLength();i++){
                        Node node= nodeList.item(i);
                        if (node.getNodeType()==Node.ELEMENT_NODE){
                            Element element=(Element) node;
                            stringBuilder.append("\nName: ").append(getValue("name",element));
                            stringBuilder.append("\nstate: ").append(getValue("state",element));
                            stringBuilder.append("\nnation: ").append(getValue("nation",element));
                            stringBuilder.append("\ntemperature: ").append(getValue("temperature",element));
                            stringBuilder.append("\nlatitude: ").append(getValue("lat",element));
                            stringBuilder.append("\nlongitude: ").append(getValue("long",element));
                            stringBuilder.append("\n..........");
                        }

                    }
                    resulttextview.setText(stringBuilder.toString());
                    inputStream.close();

                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"error parsing"+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
        parsejsonbutton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String json;
                StringBuilder stringBuilder=new StringBuilder();
                try {
                    InputStream is=getAssets().open("city.json");
                    int size= is.available();

                    byte[] buffer=new byte[size]; //create buffer
                    is.read(buffer);
                    json= new String(buffer, StandardCharsets.UTF_8);

                    JSONArray array=new JSONArray(json); //convert to array...array variable consists of contents in array format
                    stringBuilder.append("json Data");
                    stringBuilder.append("\n.........");

                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        stringBuilder.append("\nName: ").append(object.getString("name"));
                        stringBuilder.append("\nState: ").append(object.getString("state"));
                        stringBuilder.append("\nNation: ").append(object.getString("nation"));
                        stringBuilder.append("\nTemperature: ").append(object.getString("temperature"));
                        stringBuilder.append("\nlatitude: ").append(object.getString("lat"));
                        stringBuilder.append("\nlongitude: ").append(object.getString("long"));;
                        stringBuilder.append("\n..................");
                    }
                    resulttextview.setText(stringBuilder.toString());
                    is.close(); //file closing
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"error parsing"+e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private String getValue(String tag, Element element)
    {
        return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue(); //getnodevalue fetches the value
    }
}