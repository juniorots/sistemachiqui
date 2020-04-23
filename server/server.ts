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

    initializeDb(): mongoose.MongooseThenable {
        (<any>mongoose).Promise = global.Promise;
        return MongoClient.connect('mongodb://127.0.0.1:27017', 
        { useUnifiedTopology: true }, function(err, client) {
            const db = client.db('chiqui');
        } );
    }
        
    initRoutes(routers: Router[]): Promise<any>{
        return new Promise((resolve, reject)=>{
            try {
                this.application = restify.createServer({
                    name: 'sistemaChiqui',
                    version: '1.0.0'
                });
 
                // definition pluginsrequestify.plugins.bodyParser());
                this.application.use(mergePatchBodyParser);

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
        this.initializeDb();
        return this.initRoutes(routers).then(()=> this);
    } 
}