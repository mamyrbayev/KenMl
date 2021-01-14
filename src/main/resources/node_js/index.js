const puppeteer = require('puppeteer');
var express = require('express');
var app = express();

app.get('/', function(req, res) {
  const url = req.query.url;
    if(!url){
        res.status(400).send({
            message: 'invalid field'
        });
    }

    (async () => {
        const browser = await puppeteer.launch();
        const page = await browser.newPage();
        await page.goto(url, {waitUntil: 'networkidle0'});

        const pdf = await page.pdf({
                format: 'A4',
                printBackground: true,

            });

        await browser.close();
        res.setHeader('content-type', 'application/pdf');
        res.status(200).send(pdf);
        })();
});

app.listen(3000,'0.0.0.0');