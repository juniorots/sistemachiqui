/**
 * Sistema CHIQUI 
 * DENTISTAS [ ORTHO CHIQUI ]
 */
const pacientes = [
    {
        id: '1',
        nome: 'Jose Maria',
        email: 'jose@teste.com.br'        
    },
    {
        id: '2',
        nome: 'Maria Bonita',
        email: 'bonita@teste.com.br'        
    }    
]

export class Paciente {
    static findAll(): Promise<any[]>{
        return Promise.resolve(pacientes);
    }

    static findById(id: String): Promise<any>{
        return new Promise(resolve=>{
            const filtrado = pacientes.filter(paciente => paciente.id === id);
            let paciente = undefined
            if (filtrado.length > 0)
                paciente = filtrado[0];
            resolve(paciente);
        });
    }
}


