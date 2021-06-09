package br.edu.iancl.meupocket.controller;

import android.content.Context;

import java.util.List;

import br.edu.iancl.meupocket.dao.SiteDAO;
import br.edu.iancl.meupocket.model.Site;

public class SiteController {

    public static List<Site> allSites(Context context){
        return SiteDAO.getInstance(context).getSites();
    }

    public static void addSite(Context context, String title, String url){
        Site novo = new Site(title, url);
        SiteDAO.getInstance(context).addSite(novo);
    }

    public static void updateSite(Context context, String oldTitle, String title, String url){
        SiteDAO.getInstance(context).updateSite(oldTitle, title, url);
    }
}
