package br.edu.iancl.meupocket.controller;

import java.util.List;

import br.edu.iancl.meupocket.dao.SiteDAO;
import br.edu.iancl.meupocket.model.Site;

public class SiteController {

    public static List<Site> allSites(){
        return SiteDAO.getInstance().getSites();
    }

    public static void addSite(String site, String url){
        Site novo = new Site(site, url);
        SiteDAO.getInstance().addSite(novo);
    }

    public static void updateSite(String oldTitle, String title, String url){
        Site alterar = SiteDAO.getInstance().find(oldTitle);
        if(alterar != null){
            alterar.setTitle(title);
            alterar.setUrl(url);
        }
    }
}
