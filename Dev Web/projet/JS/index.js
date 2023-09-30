function toggle() {
  for (i = 0; i < document.getElementsByClassName('stock').length; i++) {
    document.getElementsByClassName("stock")[i].style.display = document.getElementsByClassName('stock')[i].style.display == 'block' ? "none" : "block";
  }

}

function ajoutQuantite(e){
let result = e.form[1];
let resultValue = result.valueAsNumber;
let stock = parseInt(e.parentElement.parentElement.nextElementSibling.textContent);

 if (resultValue + 1 <= stock ) {
  result.valueAsNumber++;
 
 }
}

function retirerQuantite(e){
let result=e.form[1];
let resultValue=result.valueAsNumber;
console.log(result);
if(resultValue -1 >= 0){

  result.valueAsNumber--;
}

}


(function($)
{
  
  $('.addPanier').click(function(event)
  {
    event.preventDefault();
    $.get($(this).attr('href'),{},function(data){
     if(data.error)
     {
       alert(data.message);
     }
     else
     {
       if(confirm(data.message + '. Voulez vous consulter votre panier ?'))
       {
         location.href='panier.php';
       }
       else
       {

       }
     }
    },'json');
    return false;
  });
    
  
})(jQuery);



//------------------------------------------------------------------------------------------------------------


function check_form() {
  let compteur = 0;
  var nom = document.getElementById("name").value; // récupère l'entrée nom de l'utilisateur
  var prenom = document.getElementById("fname").value; //' ' prénom de l'utilisateur
  var mail = document.getElementById("mail").value; //' ' prénom de l'utilisateur
  var homme = document.getElementById("choicemale");
  var femme = document.getElementById("choicefemale");
  var metiers = document.getElementById("metiers");
  var birthday = document.getElementById("naissance").value;
  var texte = document.getElementById("content_form").value;
  var contact=document.getElementById("contact").value;


  //Gestion date de contact
  if (contact.length != 10) {
    document.getElementById("contact").style.backgroundColor = 'red';
    document.getElementById("error_contact").style.display = 'block';
    return false;
} else {
    if (isNaN(contact.substring(0, 2)) == true || isNaN(contact.substring(3, 5)) == true || isNaN(contact.substring(6, 10)) == true || contact[2] != '/' || contact[5] != '/') {
        document.getElementById("contact").style.backgroundColor = 'red';
        document.getElementById("error_contact").style.display = 'block';
        return false;
    } else {
        if (contact.substring(3, 5) == '01' || contact.substring(3, 5) == '03' || contact.substring(3, 5) == '05' || contact.substring(3, 5) == '07' || contact.substring(3, 5) == '08' || contact.substring(3, 5) == '10' || contact.substring(3, 5) == '12') {
            if (contact.substring(0, 2) > 31) {
                document.getElementById("contact").style.backgroundColor = 'red';
                document.getElementById("error_contact").style.display = 'block';
                return false;
            } else {
                document.getElementById("contact").style.backgroundColor = 'white';
                document.getElementById("error_contact").style.display = 'none';
            }
        } else {
            if (contact.substring(3, 5) == '04' || contact.substring(3, 5) == '06' || contact.substring(3, 5) == '09' || contact.substring(3, 5) == '11') {
                if (contact.substring(0, 2) > 30) {
                    document.getElementById("contact").style.backgroundColor = 'red';
                    document.getElementById("error_contact").style.display = 'block';
                    return false;
                } else {
                    document.getElementById("naissance").style.backgroundColor = 'white';
                    document.getElementById("error_contact").style.display = 'none';
                }

            } else {
                if (contact.substring(3, 5) == '02') {
                    if (contact.substring(0, 2) > 29) {
                        document.getElementById("naissance").style.backgroundColor = 'red';
                        document.getElementById("error_contact").style.display = 'block';
                        return false;
                    } else {
                        document.getElementById("naissance").style.backgroundColor = 'white';
                        document.getElementById("error_contact").style.display = 'none';
                    }
                }
            }
        }
        document.getElementById("naissance").style.backgroundColor = 'white';
        document.getElementById("error_contact").style.display = 'none';
    }
}

  // Gestion du nom de famille
  if (nom == '' || nom.length > 30) {
      document.getElementById("name").style.backgroundColor = 'red';
      document.getElementById("error_name").style.display = 'block';
      return false;
  } else {
      document.getElementById("name").style.backgroundColor = 'white';
      document.getElementById("error_name").style.display = 'none';
  }

  // Gestion du prenom
  if (prenom == '' || prenom.length > 30) {
      document.getElementById("fname").style.backgroundColor = 'red';
      document.getElementById("error_fname").style.display = 'block';
      return false;
  } else {
      document.getElementById("fname").style.backgroundColor = 'white';
      document.getElementById("error_fname").style.display = 'none';
  }


  // Gestion du mail 
  if (mail == '' || mail.length > 30) {
      document.getElementById("mail").style.backgroundColor = 'red';
      document.getElementById("error_mail").style.display = 'block';
      return false;
  } else {

      for (let i = 0; i < mail.length; i++) {
          if (mail[i] == '@') {
              compteur += 1;
          }
      }
      if (compteur != 1) {
          document.getElementById("mail").style.backgroundColor = 'red';
          document.getElementById("error_mail").style.display = 'block';
          return false;

      } else { // dans le cas où il n'y a qu'une d'arobase
          if (mail[0] == '@' || mail[mail.length - 1] == '@' || mail[mail.length - 2] == '@' || mail[mail.length - 3] == '@') { // s'il est au début ou à la fin
              document.getElementById("mail").style.backgroundColor = 'red';
              document.getElementById("error_mail").style.display = 'block';
              return false;
          } else {
              if ((mail.substring(mail.length - 3, mail.length) == '.fr') || (mail.substring(mail.length - 4, mail.length) == '.com') || (mail.substring(mail.length - 4, mail.length) == '.net')) {
                  document.getElementById("mail").style.backgroundColor = 'white';
                  document.getElementById("error_mail").style.display = 'none';
              } else {
                  document.getElementById("mail").style.backgroundColor = 'red';
                  document.getElementById("error_mail").style.display = 'block';
                  return false;
              }
          }
      }
  }

  // Gestion du sexe
  if (homme.checked == false && femme.checked == false) {
      document.getElementById("error_sex").style.display = 'block';
  } else {
      if (homme.checked == true) {
          document.getElementById("error_sex").style.display = 'none';
      } else {
          document.getElementById("error_sex").style.display = 'none';
      }
  }

  // Gestion du métier
  if (metiers.value == '') {
      document.getElementById("metiers").style.backgroundColor = 'red';
      document.getElementById("error_job").style.display = 'block';
      return false;
  } else {
      document.getElementById("metiers").style.backgroundColor = 'white';
      document.getElementById("error_job").style.display = 'none';
  }

  //Gestion date de metiers naissance
  if (birthday.length != 10) {
      document.getElementById("naissance").style.backgroundColor = 'red';
      document.getElementById("error_birthday").style.display = 'block';
      return false;
  } else {
      if (isNaN(birthday.substring(0, 2)) == true || isNaN(birthday.substring(3, 5)) == true || isNaN(birthday.substring(6, 10)) == true || birthday[2] != '/' || birthday[5] != '/') {
          document.getElementById("naissance").style.backgroundColor = 'red';
          document.getElementById("error_birthday").style.display = 'block';
          return false;
      } else {
          if (birthday.substring(3, 5) == '01' || birthday.substring(3, 5) == '03' || birthday.substring(3, 5) == '05' || birthday.substring(3, 5) == '07' || birthday.substring(3, 5) == '08' || birthday.substring(3, 5) == '10' || birthday.substring(3, 5) == '12') {
              if (birthday.substring(0, 2) > 31) {
                  document.getElementById("naissance").style.backgroundColor = 'red';
                  document.getElementById("error_birthday").style.display = 'block';
                  return false;
              } else {
                  document.getElementById("naissance").style.backgroundColor = 'white';
                  document.getElementById("error_birthday").style.display = 'none';
              }
          } else {
              if (birthday.substring(3, 5) == '04' || birthday.substring(3, 5) == '06' || birthday.substring(3, 5) == '09' || birthday.substring(3, 5) == '11') {
                  if (birthday.substring(0, 2) > 30) {
                      document.getElementById("naissance").style.backgroundColor = 'red';
                      document.getElementById("error_birthday").style.display = 'block';
                      return false;
                  } else {
                      document.getElementById("naissance").style.backgroundColor = 'white';
                      document.getElementById("error_birthday").style.display = 'none';
                  }

              } else {
                  if (birthday.substring(3, 5) == '02') {
                      if (birthday.substring(0, 2) > 29) {
                          document.getElementById("naissance").style.backgroundColor = 'red';
                          document.getElementById("error_birthday").style.display = 'block';
                          return false;
                      } else {
                          document.getElementById("naissance").style.backgroundColor = 'white';
                          document.getElementById("error_birthday").style.display = 'none';
                      }
                  }
              }
          }
          document.getElementById("naissance").style.backgroundColor = 'white';
          document.getElementById("error_birthday").style.display = 'none';
      }
  }


  //Gestion du message
  if(texte =='' || texte.length > 800){
      document.getElementById("error_text").style.display = 'block';
  }else{
      document.getElementById("content_form").style.backgroundColor = 'white';
      document.getElementById("error_text").style.display = 'none';
  }
}

