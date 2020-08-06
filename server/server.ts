/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
import {environment} from '../common/environment';
import {Router} from '../common/router';
import {mergePatchBodyParser} from './merge-patch.parser';
import {handleError} from './error.handler'

import * as restify from 'restify';
import * as mongoose from 'mongoose';

const MongoClient = require('mongodb').MongoClient;

export class Server {

    application: restify.Server;    

    // database connection
    initializeDb(): mongoose.MongooseThenable {
        (<any>mongoose).Promise = global.Promise;
        return MongoClient.connect(environment.db.url, 
            { useUnifiedTopology: true });
    }
        
    initRoutes(routers: Router[]): Promise<any>{
        return new Promise((resolve, reject)=>{
            try {
                this.application = restify.createServer({
                    name: 'sistemaChiqui',
                    version: '1.0.0'
                });
 
                // definition plugins
                this.application.use(mergePatchBodyParser);
                this.application.use(restify.plugins.bodyParser());

                // Catching error
                this.application.on('restifyError', handleError);


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
        return this.initializeDb().then(()=>
                this.initRoutes(routers).then(()=> this));        
    } 
}