/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
import * as restify from 'restify';
import {environment} from '../common/environment';
import {Router} from '../common/router';
import * as mongoose from 'mongoose';
import * as mongodb from 'mongodb';

export class Server {

    application: restify.Server;    

    initializeDb(): Promise<any> {
        (<any>mongoose).Promise = global.Promise
            return mongoose.connect(environment.db.url, {
                useMongoClient : true 
            });
    }

    initRoutes(routers: Router[]): Promise<any>{
        return new Promise((resolve, reject)=>{
            try {
                this.application = restify.createServer({
                    name: 'sistemaChiqui',
                    version: '1.0.0'
                });
 
                this.application .use(restify.plugins.queryParser())

                // settings routes
                for (let router of routers) {
                    router.applyRoutes(this.application);
                    
                }
                // this.application.get('/info', [(req, resp, next)=>{
                //     return next(); // direcionando chamada para proximo callback [ abaixo ]
                // },(req, resp, next)=>{
                //     resp.json({
                //         //message: 'FUNCIONANDO..'
                //         browser: req.userAgent(),
                //         method: req.method,
                //         url: req.url,
                //         path: req.path(),
                //         query: req.query
                
                //     });
                //     return next();
                // }]);

                this.application .listen(environment.server.port, ()=>{
                    // console.log('Aplicacao escutando: http://localhost:3000');
                    resolve(this.application)
                });

                
            }catch(error) {
                reject();
            }
        })
    }

    bootstrap(routers: Router[] = []): Promise<Server>{
        return this.initializeDb().then(()=>
            this.initRoutes(routers).then(()=> this));
    } 
}