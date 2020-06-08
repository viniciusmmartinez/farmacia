import { Entity } from '../entity';

export class Farmacia extends Entity<Number> {
    public id: number = null;
    public nome: string = null;
    public telefone: string = null;
    public endereco: string = null;
}