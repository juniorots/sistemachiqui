/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
import {Router} from '../common/router'
import * as restify from 'restify'
import {Paciente} from './pacientes.model'

class PacientesRouter extends Router {

    constructor() {
        super();
        this.on('beforeRender', document=>{
            // visando eliminar um atributo qualquer de um model
            // document.ATRIBUTO_AQUI = undefined; 
        })
    }

    applyRoutes(application : restify.Server){
 
        // application.get('/pacientes', (req, resp, next)=>{
        //     Paciente.find().then(this.render(resp,next));
        // });     

        // application.get('/pacientes/:id',(req, resp, next)=>{
        //     Paciente.findById(req.params.id).then(this.render(resp,next));
        // });

        // application.post('/pacientes', (req, resp, next)=>{
        //     let paciente = new Paciente(req.body);            
        //     paciente.save().then(this.render(resp,next));
        // }); 

        // application.put('/pacientes/:id', (req, resp, next)=>{
        //     const options = {overwrite: true}
        //     Paciente.update({_id: req.params.id}, req.body, options)
        //         .exec().then(result=>{
        //             if(result.n){
        //                 return Paciente.findById(req.params.id)
        //             }
        //             return resp.send(404);
        //         }).then(this.render(resp,next))
        // });

        // application.patch('/pacientes/:id', (req, resp, next)=>{
        //     const options = {new : true};
        //     Paciente.findByIdAndUpdate(req.params.id, req.body, options).then(this.render(resp,next))
        // });

        // application.del('/pacientes/:id', (req, resp, next)=>{
        //     Paciente.remove({_id:req.params.id}, req.body).then((resultado: any)=>{
        //         if(resultado.result.n) {
        //             resp.send(204);
        //             return next();
        //         }
        //         resp.send(404);
        //         return next();
        //     });
        // });

    }
}

export const pacientesRouter = new PacientesRouter();
