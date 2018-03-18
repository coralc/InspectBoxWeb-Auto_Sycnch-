 function mnubarCreateSubMenu(mainObj) {
  var itemHeight = 13;
 var lg=' #{languageDetails.language}' ;
  var id = mainObj.id;
  var absLoc = getElementPosition(mainObj);
  var absSiz = getElementSize(mainObj);
  for (;;) {
    if (id == 'menuGroup.reports') {
      mbSubMenuObj = mnubarCreateMenuFrame(absLoc,absSiz,((7*itemHeight)+7));
      mbSubMenuObj.innerHTML = 
        "<table class='menuSubFrame' cellspacing='0' cellpadding='0'>" +
        "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_niveau.jsf?id=0','_self');\" title=\"Param&eacute;trage des niveaux\">Niveaux</td></tr>" +
        "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_objet.jsf?id=0','_self');\" title=\"Param&eacute;trage des objets\">Objets</td></tr>" +
                "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_objet1.jsf?id=0','_self');\" title=\"Affectation des Equipes\">Affecter les &eacute;quipes</td></tr>" +
         "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_choix.jsf','_self');\" title=\"Param&eacute;trage des choix\">Choix</td></tr>" +
         "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_utilisateur.jsf','_self');\" title=\"Param&eacute;trage des utilisateurs\">Utilisateurs</td></tr>" +
         "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./modifier_libelle.jsf','_self');\" title=\"Param&eacute;trage des libell&eacute;s\">Libell&eacute;s</td></tr>" +         
         "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_status.jsf','_self');\" title=\"Param&eacute;trage des Statuts\">Statuts</td></tr>" +         
          
         "</table>";
      break;
    }
    if (id == 'menuGroup.reportsen') {
        mbSubMenuObj = mnubarCreateMenuFrame(absLoc,absSiz,((7*itemHeight)+7));
        mbSubMenuObj.innerHTML = 
          "<table class='menuSubFrame' cellspacing='0' cellpadding='0'>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_niveau.jsf?id=0','_self');\" title=\"Setting levels\">Levels</td></tr>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_objet.jsf?id=0','_self');\" title=\"PSetting objekt\">Objekt</td></tr>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_objet1.jsf?id=0','_self');\" title=\"allocation of teams\">allocation of teams</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_choix.jsf','_self');\" title=\"Setting Values\">Values</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_utilisateur.jsf','_self');\" title=\"Setting users\">Users</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./modifier_libelle.jsf','_self');\" title=\"Setting labels\">Labels</td></tr>" +         
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_status.jsf','_self');\" title=\"Setting Status\">Status</td></tr>" +         
           
           "</table>";
        break;
      }
    if (id == 'menuGroup.reportsde') {
        mbSubMenuObj = mnubarCreateMenuFrame(absLoc,absSiz,((7*itemHeight)+7));
        mbSubMenuObj.innerHTML = 
          "<table class='menuSubFrame' cellspacing='0' cellpadding='0'>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_niveau.jsf?id=0','_self');\" title=\"Niveaus verwalten\">Ebenen</td></tr>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_objet.jsf?id=0','_self');\" title=\"Objekt verwalten \">Objekt</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_objet1.jsf?id=0','_self');\" title=\"Zuordnung von Teams\">Zuordnung von Teams</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_choix.jsf','_self');\" title=\"Messwerte verwalten\">Messwerte</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_utilisateur.jsf','_self');\" title=\"Benutzer verwalten\">Benutzer</td></tr>" +
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./modifier_libelle.jsf','_self');\" title=\" Bezeichnungen verwalten\">Bezeichnung</td></tr>" +         
           "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_status.jsf','_self');\" title=\" Bezeichnungen Status\">Status</td></tr>" +         
            
           "</table>";
        break;
      }
    if (id == 'menuGroup.impression') {
      mbSubMenuObj = mnubarCreateMenuFrame(absLoc,absSiz,((1*itemHeight)+6));
      mbSubMenuObj.innerHTML = 
        "<table class='menuSubFrame' cellspacing='0' cellpadding='0'>" +
        "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./liste_code_barre.jsf','_self');\" title=\" Barcodes drucken\">Barcode</td></tr>" +
        "</table>";
      break;
    }
    if (id == 'menuGroup.upload') {
        mbSubMenuObj = mnubarCreateMenuFrame(absLoc,absSiz,((1*itemHeight)+6));
        mbSubMenuObj.innerHTML = 
          "<table class='menuSubFrame' cellspacing='0' cellpadding='0'>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./uploadfile.jsf','_self');\" title=\" Upload\">upload</td></tr>" +
          "</table>";
        break;
      }
    if (id == 'menuGroup.countreports') {
        mbSubMenuObj = mnubarCreateMenuFrame(absLoc,absSiz,((2*itemHeight)+2));
        mbSubMenuObj.innerHTML = 
          "<table class='menuSubFrame' cellspacing='0' cellpadding='0'>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./last_index.jsf?id=0','_self');\" title=\"AreaID count\">AreaID index</td></tr>" +
          "<tr class='menuSubItemRow'><td class='menuSubItemCol' height='"+itemHeight+"' onclick=\"javascript:openURL('./upload_data.jsf?id=0','_self');\" title=\"Import xls loop\">Import xls</td></tr>" +
           
           "</table>";
        break;
      }
    break; // error
  }
  if (mbSubMenuObj) { document.body.appendChild(mbSubMenuObj); }
  return mbSubMenuObj;
}