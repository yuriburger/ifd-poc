export class Order {
  public id: number;
  public numberOfItems: number;
  public status: string;

  constructor(id: number, numberOfItems: number, status: string) {
    this.id = id;
    this.numberOfItems = numberOfItems;
    this.status = status;
  }
}
