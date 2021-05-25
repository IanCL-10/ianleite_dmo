package br.edu.iancl.meupocket.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.meupocket.model.Site;

public class SiteDAO {

    private static SiteDAO instance = null;
    private List<Site> siteList;

    private SiteDAO(){
        siteList = new ArrayList<>();
        siteList.add(new Site("google", "www.google.com.br"));
        siteList.add(new Site("uol", "www.uol.com.br"));
        siteList.add(new Site("ifsp", "www.ifsp.com.br"));
        siteList.add(new Site("ifsp araraquara", "arq.ifsp.edu.br"));
        siteList.add(new Site("moodle araraquara", "moodle.arq.ifsp.edu.br"));
        siteList.add(new Site("developers", "developers.android.com"));
        siteList.add(new Site("Stackoverflow", "stackoverflow.com"));
        siteList.add(new Site("Amazon Music", "music.amazon.com"));
        siteList.add(new Site("Brasil", "www.gov.br"));
    }

    public static synchronized SiteDAO getInstance(){
        if(instance == null){
            instance = new SiteDAO();
        }
        return instance;
    }

    public List<Site> getSites(){
        return siteList;
    }

    public void addSite(Site site){
        if(site != null){
            siteList.add(site);
        }
    }

    public Site find(int i){
        return siteList.get(i);
    }

    public Site find(String title){
        for (Site s: siteList){
            if (s.getTitle().equals(title)){
                return s;
            }
        }
        return null;
    }
}
