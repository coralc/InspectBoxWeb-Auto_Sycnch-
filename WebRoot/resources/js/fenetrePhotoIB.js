function affichage_popup(nom_de_la_page, nom_interne_de_la_fenetre)
{
	  width = 500;
      height = 740;
      if(window.innerWidth)
      {
              var left = (window.innerWidth-width)/2;
              var top = (window.innerHeight-height)/2;
      }
      else
      {
              var left = (document.body.clientWidth-width)/2;
              var top = (document.body.clientHeight-height)/2;
      }
      window.open (nom_de_la_page, nom_interne_de_la_fenetre, config='top='+top+', left='+left+', width='+width+', height='+height+', toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, directories=no, status=no')
}