/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
import {environment} from '../common/environment';
import {Router} from '../common/router';
import {mergePatchBodyParser} from './merge-patch.parser';

import * as restify from 'restify';
import * as mongoose from 'mongoose';
// import * as client from 'client';

const MongoClient = require('mongodb').MongoClient;

export class Server {

    application: restify.Server;    

    initializeDb(): mongoose.MongooseThenable {
        (<any>mongoose).Promise = global.Promise;
        return MongoClient.connect('mongodb://127.0.0.1:27017', function(err, client) {
            const db = client.db('chiqui');
        });
    }
        
    initRoutes(routers: Router[]): Promise<any>{
        return new Promise((resolve, reject)=>{
            try {
                this.application = restify.createServer({
                    name: 'sistemaChiqui',
                    version: '1.0.0'
                });
 
                // definition plugins
                this.application.use(restify.plugins.queryParser());
                this.application.use(restify.plugins.bodyParser());
                this.application.use(mergePatchBodyParser);


                // settings routes
                for (let router of routers) {
                    router.applyRoutes(this.application);                    
                }

                this.application .listen(environment.server.port, ()=>{                    
                    resolve(this.application)
                });

                
            }catch(error) {
                reject();
            }
        })
    }

    bootstrap(routers: Router[] = []): Promise<Server>{   
        this.initializeDb();
        return this.initRoutes(routers).then(()=> this);
    } 
}