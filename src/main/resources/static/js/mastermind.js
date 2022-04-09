temp = document.getElementsByTagName("template")[0];
var cont = document.getElementsByClassName("board")[0];
console.log(cont);
var clon = temp.content.cloneNode(true);
var gomb = document.getElementById("megadd");
var ready = document.getElementsByClassName("ready")[0];
var maxSorok =10;
var currentSor = maxSorok;
var currentTipp;
var poz = 117; //
var adat = [];
var id;
var kesz;
const url = 'http://localhost:8080';
for (i = 1; i < maxSorok +1 ; i++ ){
    //console.log(i);
    var clon = temp.content.cloneNode(true);
    //console.log(clon)
    var c = clon.querySelector(".sor").id = "sor" + i;
    //console.log(c)
    clon.querySelector("#tipp0_1").setAttribute('id','tipp' + i + '_1');
    clon.querySelector("#tipp0_2").setAttribute('id','tipp' + i + '_2');
    clon.querySelector("#tipp0_3").setAttribute('id','tipp' + i + '_3');
    clon.querySelector("#tipp0_4").setAttribute('id','tipp' + i + '_4');
    clon.querySelector("#ered0_1").setAttribute('id','ered' + i + '_1');
    clon.querySelector("#ered0_2").setAttribute('id','ered' + i + '_2');
    clon.querySelector("#ered0_3").setAttribute('id','ered' + i + '_3');
    clon.querySelector("#ered0_4").setAttribute('id','ered' + i + '_4');
    cont.appendChild(clon);
document.body.appendChild(clon);
}

function start(){
    poz = poz + maxSorok * 38;
    console.log(poz);
    gomb.style.top = poz + "px";
    gomb.style.display = "block";
    var s = document.getElementById('sor' + currentSor);
    s.classList.add("currentsor");
    var t = document.getElementById('tipp' + currentSor + '_1');
    t.classList.add("currenttipp");
    currentTipp = 1;
    console.log(s);
    sendrequest(url+ '/mastrm/start', 'POST','text', null,'text', function(data) {
        console.log(data);
        id = data;
    })
}

function _ready(){
    //console.log(adat);
    let sor = currentSor;
    let body = JSON.stringify({
        'tippek': adat,
        'id': id
    })
    sendrequest(url+ '/mastrm/playing', 'POST','JSON', body, 'JSON' ,function(data) {
        console.log(typeof data);
        kesz = data.kesz;
        eredmenyKirajzol(data, sor);
        if (data.kesz) {
            
            sendrequest(url+ '/mastrm/kesz', 'POST','text', id, 'JSON' ,function(data) {
                console.log(typeof data);
                megoldasKirajzol(data)
            })
        }
    })
    document.getElementById('sor' + currentSor).classList.remove("currentsor");
    ready.style.display = "none";
    if (currentSor > 1) {
        poz = poz - 38;
        gomb.style.top = poz + "px";
        currentTipp = 1;
        currentSor--;
        if (kesz) {
            var s = document.getElementById('sor' + currentSor);
            s.classList.add("currentsor");
            var t = document.getElementById('tipp' + currentSor + '_' + currentTipp);
            t.classList.add("currenttipp");
        }
        
    }
}
function megoldasKirajzol(data){

    for (i=1; i < 5; i++){
        var t = document.getElementById('tipp0_' + i);

        t.style.background = data[i-1];
    }
}
function eredmenyKirajzol(data, sor){
  
    let eredmenyek = data.eredmeny;
    //console.log(eredmenyek);

    for (i=1; i < 5; i++){
        
        
        if (eredmenyek[i-1] == 1){
            var t = document.getElementById('ered' + 
            sor + '_' + i);
            console.log(t);
            t.style.background = 'white';
        }else if (eredmenyek[i-1] == 2){
            var t = document.getElementById('ered' + 
            sor + '_' + i);
            t.style.background = 'black';
        }
    }
}
function szinez(szin){
    if (currentSor >= 1) {
        //console.log(currentTipp);
        var t = document.getElementById('tipp' + 
        currentSor + '_' + currentTipp);
        
        t.style.background = szin; 
        adatToltes(szin);
        console.log(adat);
        t.classList.remove("currenttipp");
        if (currentTipp == 4) ready.style.display = "block";
        currentTipp++;
        
        if (currentTipp <= 4) {
            console.log(currentTipp);
            document.getElementById('tipp' + currentSor + '_' + currentTipp).classList.add("currenttipp");            
        } 
    }
}
function adatToltes(szin){
    switch (szin) {
        case 'blue': adat[currentTipp-1] = 1;
        break;
        case 'red': adat[currentTipp-1] = 2;
        break;
        case 'yellow': adat[currentTipp-1] = 3;
        break;
        case 'green': adat[currentTipp-1] = 4;
        break;
        case 'white': adat[currentTipp-1] = 5;
        break;
        case 'black': adat[currentTipp-1] = 6;
        break;
    }
}

function sendrequest(url, method,btype, body,type, callback) {
    var xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
          if  (type == 'JSON'){
                callback(JSON.parse(xhr.responseText))
          }else {
            callback(xhr.responseText)
          }
      }
    }
    xhr.open(method, url)
        if  (btype == 'JSON'){
            xhr.setRequestHeader('content-type', 'application/json')
        }else {
            xhr.setRequestHeader('content-type', 'text/plain')
            console.log('adat');
        }
   
    xhr.send(body)
}   