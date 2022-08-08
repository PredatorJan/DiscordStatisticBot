export class ComponentError {
    name: string;
    message: string;
    
    constructor(name: string, message: string) {
        this.name = name;
        this.message = message;
    }
}

export class UserTableException extends ComponentError{
    constructor(name: string, message: string) {
        super(name, message);
    }
}