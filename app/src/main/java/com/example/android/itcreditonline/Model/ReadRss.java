package com.example.android.itcreditonline.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.itcreditonline.NewsAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Simeon Angelov on 30.9.2016 г..
 */

public class ReadRss extends AsyncTask<Void,Void,Void> {
    ArrayList<FeedItem> feedsItems;
    private Context context;
    private String address = "http://www.ft.com/rss/companies/banks";
    private ProgressDialog progressDialog;
    private URL url;
    RecyclerView recyclerView;

    public ReadRss(Context context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    public ArrayList<FeedItem> getFeedsItems() {
        return feedsItems;
    }

    public Document getData(){
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        NewsAdapter adapter = new NewsAdapter(feedsItems,context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ProcessXml(getData());
        return null;
    }

    private void ProcessXml(Document data) {
        if(data != null){
           feedsItems = new ArrayList<>();

            //get channel element
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);

            //get item elements
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node currentChild = items.item(i);
                if(currentChild.getNodeName().equalsIgnoreCase("item")){
                    FeedItem item = new FeedItem();
                    NodeList itemChildren = currentChild.getChildNodes();
                    for (int j = 0; j < itemChildren.getLength(); j++) {
                        Node current = itemChildren.item(j);
                        if(current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        }else if(current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        }
                    }
                    feedsItems.add(item);
                    Log.d("itemTitle", item.getTitle());
                    Log.d("itemDescription", item.getDescription());
                    Log.d("itemLink", item.getLink());
                    Log.d("itemPubDate", item.getPubDate());
                }
            }
        }
    }
}
