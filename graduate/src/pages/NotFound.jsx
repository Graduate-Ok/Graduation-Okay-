import react from 'react';

// 404 not found 
const NotFound = () =>{
    return (
        <div
            style = {{
                display : 'flex',
                alignItems : 'center',
                justifyContent : 'center',
                fontSize : 64,
                position : 'absolute',
                width : '100%',
                height : '100%',
            }}>
                    404 NOT FOUND
        </div>
    );
};

export default NotFound;