import { Entity } from '../entity';

export class Farmacia extends Entity<Number> {
    public id: number = null;
    public name: string = null;
    public tel: string = null;
    public address: string = null;
}