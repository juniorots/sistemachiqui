/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
import {Router} from '../common/router'
import * as restify from 'restify'
import {Paciente} from './pacientes.model'

class PacientesRouter extends Router {
    applyRoutes(application : restify.Server){
 
        application.get('/pacientes', (req, resp, next)=>{
            Paciente.find().then(pacientes=>{
                resp.json(pacientes);
                return next();
            });
        });     

        application.get('/pacientes/:id',(req, resp, next)=>{
            Paciente.findById(req.params.id).then(paciente=>{
                if(paciente) {
                    resp.json(paciente);
                    return next();
                }
                resp.send(404);
                return next();
            });
        });

        application.post('/pacientes', (req, resp, next)=>{
            let paciente = new Paciente();
            
            paciente.save();
        }); 
    }
}

export const pacientesRouter = new PacientesRouter();
