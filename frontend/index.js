const express = require('express');
const {createProxyMiddleware} = require('http-proxy-middleware');
const path = require('path');
const app = express();

app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname, '/src/index.html'));
});

app.use(express.static('src'))

app.use('/api', createProxyMiddleware(
        {
            target: 'http://localhost:8080',
            changeOrigin: true,
            pathRewrite: {'/api': ''}
        }
    )
);
app.listen(3000);
