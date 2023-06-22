package business;


import business.anemo.AnemoController;
import business.commercial.*;
import business.map.*;
import business.pann.SolarPanelController;
import server.Request;
import server.Response;

public class Router {
    public static Response go(Request req) {
        AnemoController anemoController = new AnemoController(req.co);
        SolarPanelController solarPanelController = new SolarPanelController(req.co);

        switch (req.getProcess()) {

            // Begining-of-Commercial-Case
            case "OFFER_SHOW_ALL":
                return DisplayOffer.playShow(req);
            case "OFFER_SPEC_ALL":
                return DisplayOffer.playSpec(req);
            case "OFFER_PROD_ALL":
                return DisplayProduct.playprod(req);
            case "OFFER_CREATE_IN_1":
                return DefineOffer.insertCreate1(req);
            case "OFFER_CREATE_IN_2":
                return DefineOffer.insertCreate2(req);
            case "OFFER_SPEC_IN_1":
                return DefineOffer.insertSpec1(req);
            case "OFFER_SPEC_IN_2":
                return DefineOffer.insertSpec2(req);
            case "OFFER_SPEC_UP_1":
                return ActualizeOffer.updateSpec1(req);
            case "OFFER_SPEC_UP_2":
                return ActualizeOffer.updateSpec2(req);
            case "OFFER_SHOW_DEL_1":
                return RemoveOffer.supShow1(req);
            case "OFFER_SHOW_DEL_2":
                return RemoveOffer.supShow2(req);
            case "OFFER_SHOW_DEL_3":
                return RemoveOffer.supShow3(req);
            case "OFFER_SHOW_SEEK":
                return SearchOffer.seekShow(req);
            // End-of-Case-Declaration

            //Start
            case "READ_ANEMOVALUE_BY_LABEL":
                return anemoController.fetchAllAnemoValueByLabel(req);
            case "READ_ALL_LABEL":
                return anemoController.fetchAllObjectLabel();
            case "CREATE_UPDATE_CONFIG_ANEMO":
                return anemoController.postOrPutAnemoConfig(req);
            case "DIFF_CONFIG":
                return anemoController.difference(req);
            case "DIFF_GUST":
                return  anemoController.differenceGust(req);
            case "NUMB_ANEMO_CONFIG":
                return anemoController.getNumberConfigured(req);
            case "NUMB_ANEMO" :
                return  anemoController.getNumber(req);
            case "SELECT_CONFIG":
                return  anemoController.fetchAllConfiguration(req);
            case "INSERT_VALUE":
                return anemoController.insertvalue();
            //End

            case "READ_SOLAR_ID":
                return solarPanelController.fetchAllSolarPanelById(req);
            case "READ_All_SOLAR_PLACED":
                return solarPanelController.fetchAllPlacedSolarPanelByLabel();
            case "READ_ALL_ENERGY_PANEL":
                return solarPanelController.fetchAllEnergyValues();
            case "READ_TYPE_PANEL":
                return solarPanelController.fetchAllSolarByType(req);
            case "READ_ENERGY_PANEL_TYPE":
                return solarPanelController.fetchAllEnergieByType(req);
            case "READ_ID_PANEL_TYPE":
                return solarPanelController.getAllIdByType(req);
            case "READ_PlACES_WENERGY":
                return solarPanelController.getAllPlacesWenergie();
            case "READ_Energy":
                return solarPanelController.getAllEnergie();
            case "READ_PlACES":
                return solarPanelController.getAllPlaces();
            case "CALC_ENERGY":
                return solarPanelController.fetchAllWithEnergy(req);
            case "CREATE_PAN":
                return solarPanelController.createtoMap();
            case "SEARCH_PAN":
                return solarPanelController.serchaPanel();
            case "PANEL_MAP":
                return solarPanelController.paneltoaMap(req);
            case "TOT_PAN":
                return solarPanelController.totalPanel();
            case "MAP_GET_MAP_OBJECT":
                return GetMapObject.run(req);
            case "POST_MAP_OBJECT":
                return PostMapObject.run(req);
            case "DELETE_MAP_OBJECT":
                return DeleteMapObject.run(req);
            case "UPDATE_MAP_OBJECT":
                return UpdateMapObject.run(req);
            case "POST_GEO_POINT":
                return PostGeoPoint.run(req);
            case "DELETE_GEO_POINT":
                return DeleteGeoPoint.run(req);
            default:
                return new Response("error");
        }

    }
}
