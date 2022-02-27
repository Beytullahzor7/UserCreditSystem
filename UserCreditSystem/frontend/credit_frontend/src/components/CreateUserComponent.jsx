import React, { Component } from 'react';
import UserService from '../services/UserService';

class CreateUserComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            // step 2
            id: this.props.match.params.id,
            identityNumber: "",
            name: "",
            surname: "",
            salary: "",
            phoneNumber: "",
            creditScore: "",
        };


        this.changeidentityNumberHandler = this.changeidentityNumberHandler.bind(this);
        this.changenameHandler = this.changenameHandler.bind(this);
        this.changesurnameHandler = this.changesurnameHandler.bind(this);
        this.changesalaryHandler = this.changesalaryHandler.bind(this);
        this.changephoneNumberHandler = this.changephoneNumberHandler.bind(this);
        this.changecreditScoreHandler = this.changecreditScoreHandler.bind(this);
        this.saveOrUpdateUser = this.saveOrUpdateUser.bind(this);
    }

    changeidentityNumberHandler = (event) => {
        this.setState({ identityNumber: event.target.value });
    };

    changenameHandler = (event) => {
        this.setState({ name: event.target.value });
    };

    changesurnameHandler = (event) => {
        this.setState({ surname: event.target.value });
    };

    changesalaryHandler = (event) => {
        this.setState({ salary: event.target.value });
    };

    changephoneNumberHandler = (event) => {
        this.setState({ phoneNumber: event.target.value });
    };

    changecreditScoreHandler = (event) => {
        this.setState({ creditScore: event.target.value });
    };

    // step 3
    //COMPONENTDIDMOUNT
    componentDidMount() {
        // step 4
        if (this.state.id === "_add") {
            return;
        } else {
            UserService.getUserById(this.state.id).then((res) => {
                let user = res.data;
                this.setState({
                    identityNumber: user.identityNumber,
                    name: user.name,
                    surname: user.surname,
                    salary: user.salary,
                    phoneNumber: user.phoneNumber,
                    creditScore: user.creditScore,
                });
            });
        }
    }

    saveOrUpdateUser = (u) => {
        u.preventDefault();
        let user = {
            identityNumber: this.state.identityNumber,
            name: this.state.name,
            surname: this.state.surname,
            salary: this.state.salary,
            phoneNumber: this.state.phoneNumber,
            creditScore: this.state.creditScore,
        };
        console.log("user => " + JSON.stringify(user));

        // step 5
        if (this.state.id === "_add") {
            UserService.createUser(user).then((res) => {
                this.props.history.push("/users");
            });
        } else {
            UserService.updateUser(user, this.state.id).then((res) => {
                this.props.history.push("/users");
            });
        }
    };

    cancel() {
        this.props.history.push("/users");
    }

    getTitle() {
        if (this.state.id === "_add") {
            return <h3 className="text-center">Create User</h3>;
        } else {
            return <h3 className="text-center">Update User</h3>;
        }
    }


    render() {
        return (
            <div>
                {" "}
                <br></br>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            {this.getTitle()}
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> identityNumber </label>
                                        <input
                                            placeholder="identityNumber"
                                            name="identityNumber"
                                            className="form-control"
                                            value={this.state.identityNumber}
                                            onChange={this.changeidentityNumberHandler}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label> name: </label>
                                        <input
                                            placeholder="name"
                                            name="name"
                                            className="form-control"
                                            value={this.state.name}
                                            onChange={this.changenameHandler}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label> surname </label>
                                        <input
                                            placeholder="surname"
                                            name="surname"
                                            className="form-control"
                                            value={this.state.surname}
                                            onChange={this.changesurnameHandler}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label> salary </label>
                                        <input
                                            placeholder="salary"
                                            name="salary"
                                            className="form-control"
                                            value={this.state.salary}
                                            onChange={this.changesalaryHandler}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label> phoneNumber </label>
                                        <input
                                            placeholder="phoneNumber"
                                            name="phoneNumber"
                                            className="form-control"
                                            value={this.state.phoneNumber}
                                            onChange={this.changephoneNumberHandler}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label> creditScore </label>
                                        <input
                                            placeholder="creditScore"
                                            name="creditScore"
                                            className="form-control"
                                            value={this.state.creditScore}
                                            onChange={this.changecreditScoreHandler}
                                        />
                                    </div>
                                    <br />

                                    <button
                                        className="btn btn-warning"
                                        onClick={this.saveOrUpdateUser}
                                    >
                                        Save
                                    </button>
                                    <button
                                        className="btn btn-danger"
                                        onClick={this.cancel.bind(this)}
                                        style={{ marginLeft: "10px" }}
                                    >
                                        Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default CreateUserComponent;