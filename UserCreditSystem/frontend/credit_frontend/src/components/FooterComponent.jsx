import React, { Component } from 'react';

class FooterComponent extends Component {

    constructor(props){
        super(props)
        this.state = {};
    }

    render() {
        return (
            <div>
                <footer className='footer'>
                    <p className='text-muted'> &copy; All Rights Reserved</p>

                </footer>
            </div>
        );
    }
}

export default FooterComponent;