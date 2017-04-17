package com.yoval.community.chatapp;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Yovanna on 2017-04-15.
 */

public class Tools {


    public static String findCategory(Bundle bundle) {

        if(bundle!=null)
        {
            String category = (String) bundle.get("Category");
            String tableName= "";
            switch (category)
            {
                case "Reparations":
                    tableName="Reparations";
                    break;
                case "Lesson de musique":
                    tableName="Music";
                    break;
                case "Aide aux aînes":
                    tableName="Elder";
                    break;
                case "Animaux de compagnie":
                    tableName="Pets";
                    break;
                case "Securité du quartier":
                    tableName="Security";
                    break;
                case "Preparer des examens":
                    tableName="Review";
                    break;
                case "Partage de nourriture":
                    tableName =  "Food";
                    break;
                case  "Covoiturage":
                    tableName="Carpooling";
                    break;
                case "Faire des sports":
                    tableName="Sports";
                    break;
                case "Nettoyage de maison":
                    tableName="Cleaning";
                    break;
                case "Autre":
                    tableName= "Other";
                    break;


            }
            return tableName;
        }
        return "";
    }
}
