var express = require("express")
var mysql = require("mysql")
var app = express()
var bodyParser = require('body-parser');
app.use(bodyParser.json());

//database connection
var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : '',
    database:'prj_mobile'
});
connection.connect();
// server creation
var server = app.listen(8082,function(){
    var host = server.address().address
    var port = server.address().port
});

//rest service
app.get('/', function (req, res) {
    res.render('index', {});
});
app.post('/addteam', function(req , res ) {
    console.log(req.body)
    var team = JSON.parse(JSON.stringify(req.body));
    var query = "INSERT INTO team VALUES (?,?,?)";
    connection.query(query, [team.id_team,team.team_name, team.continent], function (error, results) {
        if (error) throw error;
        res.send("sucsses");
    });
});

app.get('/getTeams',function(req,res){
    var query = "select * from team";
   connection.query(query,function(error,results){
    if (error) throw error;
    res.send(results);
})
});
app.get('/getTeam/:id_team',function(req,res){
    var query = "select * from team where id_team = " + req.params.id_team;
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results);
    })
});
app.get('/getPlayers',function(req,res){
    var query = "select * from player";
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results);
    })
});
app.get('/getPlayer/:id_player',function(req,res){
    var query = "select * from player where id_team = " + req.params.id_player;
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results);
    })
});
app.get('/getPatients',function(req,res){
    var query = "select * from patient";
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results);
    })
});
app.get('/getPatient/:phone/:password',function(req,res){
    var query = "select * from patient where phone = '" + req.params.phone+"' and password = '"+req.params.password+"'";
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results[0]);
    })
});
app.get('/getDoctors/:town/:Speciality',function(req,res){
    var query = "select * from doctor where town = '" + req.params.town+"' and Speciality = '"+req.params.Speciality+"'";
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results);
    })
});
app.get('/getDoctor/:nssd',function(req,res){
    var query = "select * from doctor where nssd = '" + req.params.nssd+"'";
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results[0]);
    })
});
app.get('/getDoctors',function(req,res){
    var query = "select * from doctor";
    connection.query(query,function(error,results){
        if (error) throw error;
        res.send(results);
    })
});
app.post('/addpatient', function(req , res ) {
    console.log(req.body)
    var patient = JSON.parse(JSON.stringify(req.body));
    var query = "INSERT INTO patient VALUES (?,?,?,?,?,?)";
    connection.query(query, [patient.nssp, patient.first_name, patient.last_name, patient.adress, patient.phone, patient.password], function (error, results) {
        if (error) throw error;
        res.send("sucsses");
    });
});
app.post('/changepw', function(req , res ) {
    var str = JSON.parse(JSON.stringify(req.body));
    var query = "UPDATE patient SET password = '"+str[1]+"' WHERE patient.nssp = '"+str[0]+"'";
    connection.query(query, function (error, results) {
        if (error) throw error;
        res.send("sucsses");
    });
});
app.get('/sendsms/:phone/:pw', function(req , res ) {
    var client = require('twilio')(
        "AC24fb661afc4cad1f723a4221acd6fc65",
        "d9620a248f76d17a7ccfc5c687af0c3d"
      );
      client.messages.create({
        from: "+12244125394",
        to: "+213"+req.params.phone,
        body: "votre mot de passe est "+req.params.pw
      }).then((message) => console.log(message.sid));
    });
    app.post('/addtreatingdoctor/', function(req , res ) {
        var str = JSON.parse(JSON.stringify(req.body));
        var query = "INSERT INTO treating_doctor VALUES (?,?)";
        connection.query(query, [str[0], str[1]], function (error, results) {
            if (error) throw error;
            res.send("sucsses");
        });
    });
    app.get('/getTreating_doctor/:nssp',function(req,res){
        var query = "select nssd from treating_doctor where nssp = '"+req.params.nssp+"'";
        connection.query(query,function(error,results){
            var str = new Array();
            for (i in results)
                str.push(results[i].nssd);
            if (error) throw error;
            res.send(str);
        })
    });
    app.get('/getDoctor2/:tel/:password',function(req,res){
        var query = "select * from doctor where tel = '" + req.params.tel+"' and password = '"+req.params.password+"'";
        connection.query(query,function(error,results){
            if (error) throw error;
            res.send(results[0]);
        })
    });
    app.post('/addtreatingdoctor/', function(req , res ) {
        var str = JSON.parse(JSON.stringify(req.body));
        var query = "INSERT INTO treating_doctor VALUES (?,?)";
        connection.query(query, [str[0], str[1]], function (error, results) {
            if (error) throw error;
            res.send("sucsses");
        });
    });
    app.post('/sendinvitation/', function(req , res ) {
        var str = JSON.parse(JSON.stringify(req.body));
        var query = "INSERT INTO invitation VALUES (?,?)";
        connection.query(query, [str[0], str[1]], function (error, results) {
            if (error) throw error;
            res.send("sucsses");
        });
    });
    app.get('/deleateinvitation/:nssd/:nssp', function(req , res ) {
        var str = JSON.parse(JSON.stringify(req.body));
        var query = "DELETE from invitation WHERE invitation.nssd = '"+req.params.nssd+"' and invitation.nssp = '"+req.params.nssp+"'";
        connection.query(query, function (error, results) {
            if (error) throw error;
            res.send("sucsses");
        });
    });
    app.get('/getPatientsPers/:nssd',function(req,res){
        var query = "select patient.nssp,patient.first_name,patient.last_name,patient.adress,patient.phone,patient.password from patient join treating_doctor ON treating_doctor.nssd = '"+req.params.nssd+"' and patient.nssp = treating_doctor.nssp  ORDER BY patient.last_name";
        connection.query(query,function(error,results){
            if (error) throw error;
            res.send(results);
        })
    });
    app.get('/getInvitations/:nssd',function(req,res){
        var query = "select patient.nssp,patient.first_name,patient.last_name,patient.adress,patient.phone,patient.password from patient join invitation ON invitation.nssd = '"+req.params.nssd+"' and patient.nssp = invitation.nssp  ORDER BY patient.last_name";
        connection.query(query,function(error,results){
            if (error) throw error;
            res.send(results);
        })
    });
    app.get('/getInvitations2/:nssp',function(req,res){
        var query = "select nssd from invitation where nssp = '"+req.params.nssp+"'";
        connection.query(query,function(error,results){
            var str = new Array();
            for (i in results)
                str.push(results[i].nssd);
            if (error) throw error;
            res.send(str);
        })
    });
    app.post('/addtreatment', function(req , res ) {
        var treatment = JSON.parse(JSON.stringify(req.body));
        var query = "INSERT INTO treatment(nssd,nssp,duration) VALUES (?,?,?)";
        var query2= "select max(id_treatment) as id_treatment , nssd, nssp, duration from treatment";
        connection.query(query, [treatment.nssd,treatment.nssp,treatment.duration], function (error, results) {
            if (error) throw error;
        });
        connection.query(query2, function (error, results1) {
            if (error) throw error;
            res.send(results1[0]);
        });
    });
    app.post('/insert_med', function(req , res ) {
        var medication = JSON.parse(JSON.stringify(req.body));
        var query = "INSERT INTO medication(name,time,id_treatment) VALUES (?,?,?)";
        connection.query(query, [medication.name,medication.time_med,medication.Id_treatment], function (error, results) {
            if (error) throw error;
            res.send("sucsses");
        });
    });
    app.get('/getTreatments/:nssp',function(req,res){
        var query = "SELECT doctor.first_name, treatment.duration,treatment.id_treatment FROM treatment JOIN doctor ON treatment.nssp = '"+req.params.nssp+"' AND treatment.nssd = doctor.nssd";
        connection.query(query,function(error,results){
            if (error) throw error;
            var res2 = new Array();
            for (i in results){
                var str = new Array()
                str.push(results[i].first_name)
                str.push(results[i].duration)
                str.push(results[i].id_treatment)
                res2.push(str)
            }
            if (error) throw error;
            res.send(res2);
        })
    });
    app.get('/getMedications/:id',function(req,res){
        var query = "SELECT name, time FROM medication where medication.id_treatment	 = '"+req.params.id+"'";
        connection.query(query,function(error,results){
            var res2 = new Array();
            for (i in results){
                var str = new Array()
                str.push(results[i].name)
                str.push(results[i].time)
                res2.push(str)
            }
            if (error) throw error;
            res.send(res2);
        })
    });
    app.post('/insert_RDV', function(req , res ) {
        var appointment = JSON.parse(JSON.stringify(req.body));
        var query = "INSERT INTO appointment(date,time,nssd,nssp) VALUES (?,?,?,?)";
        connection.query(query, [appointment.date, appointment.time, appointment.nssd, appointment.nssp], function (error, results) {
            if (error) throw error;
            res.send("sucsses");
        });
    });
    app.get('/getTreDoc/:nssp',function(req,res){
        var query = "select doctor.* from doctor join treating_doctor on treating_doctor.nssp = '"+req.params.nssp+"'  and treating_doctor.nssd = doctor.nssd";
        connection.query(query,function(error,results){
            if (error) throw error;
            res.send(results);
        })
    });
    app.get('/getAgenda/:nssd',function(req,res){
        var query = "SELECT patient.first_name, patient.last_name, appointment.date, appointment.time FROM patient join appointment on appointment.nssd = '"+req.params.nssd+"' and appointment.nssp = patient.nssp";
        connection.query(query,function(error,results){
            var res2 = new Array();
            for (i in results){
                var str = new Array()
                str.push(results[i].first_name)
                str.push(results[i].last_name)
                str.push(results[i].date)
                str.push(results[i].time)
                res2.push(str)
            }
            if (error) throw error;
            res.send(res2);
        })
    });