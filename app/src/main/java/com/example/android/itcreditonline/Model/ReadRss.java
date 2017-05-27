package com.example.android.itcreditonline.Model;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.itcreditonline.NewsAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadRss extends AsyncTask<String, Void, Void> {
    private ArrayList<FeedItem> feedsItems;
    private Activity context;

    private URL url;
    private RecyclerView recyclerView;

    public ReadRss(Activity context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;

    }


    public Document getData(String address) {
        try {
            url = new URL(address);
            URLConnection con = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

            String inputLine;
            String fullStr = "";
            while ((inputLine = reader.readLine()) != null)
                fullStr = fullStr.concat(inputLine + "\n");

            InputStream istream = url.openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = builder.parse(istream);

            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        NewsAdapter adapter = new NewsAdapter(feedsItems, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected Void doInBackground(String... params) {
        ProcessXml(getData(params[0]));
        return null;

    }

    private void ProcessXml(Document data) {
        if (data != null) {
            feedsItems = new ArrayList<>();
            //get channel element
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);

            //get item elements
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node currentChild = items.item(i);
                if (currentChild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item = new FeedItem();
                    NodeList itemChildren = currentChild.getChildNodes();
                    for (int j = 0; j < itemChildren.getLength(); j++) {
                        Node current = itemChildren.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(current.getTextContent());
                        }
                    }
                    feedsItems.add(item);

                }
            }
        }
    }
}
