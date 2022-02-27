import React, { Component } from 'react';
import UserService from '../services/UserService';

class ListUserComponent extends Component {

    constructor(props) {
        super(props)

        //baslangıc degerler
        this.state = {
            users: []
        }

        //bind
        this.addUser = this.addUser.bind(this);
        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
    }

    //ADD
    addUser() {
        this.props.history.push("/add-user/_add");
    }

    //EDIT
    editUser(id) {
        this.props.history.push(`/add-user/${id}`);
    }

    //DELETE
    deleteUser(id) {
        UserService.deleteUser(id).then(res => {
            this.setState({
                users: this.state.users.filter(
                    (user) => user.id !== id
                ),
            });
        });
    }

    //VIEW
    viewUser(id) {
        this.props.history.push(`/apply-user/${id}`);
    }

    //DIDMOUNT: service database
    componentDidMount() {
        UserService.getUsers().then((res) => {
            this.setState({ users: res.data });
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center"> User List</h2>
                <div className="row">
                    <button className="btn btn-danger" onClick={this.addUser}> Add User</button>
                </div>
                <br></br>
                <div className="row">
                    <table className="table table-striped table-bordered">

                        <thead>
                            <tr>
                                <th> identityNumber</th>
                                <th> name</th>
                                <th> surname</th>
                                <th> salary </th>
                                <th> phoneNumber</th>
                                <th> creditScore </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map(
                                    user =>
                                        <tr key={user.id}>
                                            <td> {user.identityNumber} </td>
                                            <td> {user.name}</td>
                                            <td> {user.surname}</td>
                                            <td> {user.salary} </td>
                                            <td> {user.phoneNumber}</td>
                                            <td> {user.creditScore}</td>
                                            <td>
                                                <button style={{ marginLeft: "15px" }} onClick={() => this.viewUser(user.id)} className="btn btn-warning">Credit Application </button>
                                                <button style={{ marginLeft: "15px" }} onClick={() => this.deleteUser(user.id)} className="btn btn-danger">Delete </button>
                                                <button style={{ marginLeft: "15px" }} onClick={() => this.editUser(user.id)} className="btn btn-success">Update </button>

                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListUserComponent;