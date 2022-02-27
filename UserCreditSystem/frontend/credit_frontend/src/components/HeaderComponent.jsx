import React, { Component } from 'react';

class HeaderComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {};
    }

    render() {
        return (
            <div>
                <header>
                    <nav className='navbar navbar-expand-md navbar-primary bg-dark'>
                        <div>
                            <p className='text-center fw-bolder display-5 text-danger'>User Credit Application</p>
                        </div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;