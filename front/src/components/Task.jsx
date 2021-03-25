import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';


export const Task = (props) => {

    return (
        <Card className="root">
            <CardContent>
                <Typography variant="h4" component="h1">
                    {props.description}
                </Typography>
                <Typography className="title" color="textSecondary" gutterBottom>
                    {props.status}
                </Typography>
                <Typography className="pos" color="textSecondary">
                    {props.dueDate}
                </Typography>
                <Typography variant="body2" component="p">
                    {props.responsible}
                </Typography>
            </CardContent>
            <br/>
        </Card>
    );
}
