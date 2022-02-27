import React, { Component } from 'react';
import UserService from '../services/UserService';

class ApplyUserCreditComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            user: {}
        }
    }


    //componentDidMount
    componentDidMount(){
        UserService.creditUser(this.state.id).then( res => {
            this.setState({user: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center">Credit Application Sent</h3>
                </div>
            </div>
        )
    }
}

export default ApplyUserCreditComponent;