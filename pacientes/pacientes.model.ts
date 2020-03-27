/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
import * as mongoose from 'mongoose'

export interface Paciente extends mongoose.Document {
    nome: String,
    prontuario: String,
    telefone: String,
    cpf: String,
    rg: String
}

const pacienteSchema = new mongoose.Schema({
    nome: {
        type: String
    },
    prontuario : {
        type : String
    },
    telefone : {
        type : String
    },
    cpf : {
        type : String
    },
    rg : {
        type : String,
        // select: false // desabilita esse campo na consulta padrao - EXEMPLO
    }
});

export const Paciente = mongoose.model<Paciente>('Paciente', pacienteSchema)


