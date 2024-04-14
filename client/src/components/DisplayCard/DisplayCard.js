import { useState } from "react";
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { red } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import Button from "../Button/Button";
import styles from "./displayCard.module.css";
// import photo from "../../../../server/images/"

const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
  marginLeft: "auto",
  transition: theme.transitions.create("transform", {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function DisplayCard({posts,setPosts}) {

  const handleExpandClick = (index) => {
    const postUpdate=[...posts];
    postUpdate[index].expanded=!postUpdate[index].expanded;
    setPosts(postUpdate);
  };
  return (
    <div className={styles.main}>
    {posts?.map((post,index)=>(

    <Card sx={{ maxWidth: 345 }} key={index}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title="Shrimp and Chorizo Paella"
        subheader="September 14, 2016"
      />
      <CardMedia
        component="img"
        width={194}
        height="auto"
        src={`/images/${post?.fileName}`}
        alt={post?.caption}
      />
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
      </CardActions>
      <CardContent sx={{paddingTop:0}}>
        <Typography variant="body2" color="text.secondary">
          (User here) {post?.caption}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
      <Typography variant="body2" color="text.secondary">
          See comments..
        </Typography>
        <ExpandMore
          expand={post?.expanded}
          onClick={()=>handleExpandClick(index)}
          aria-expanded={post?.expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={post?.expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>
            (username) Comment 1
          </Typography>
          <Typography paragraph>
          (username) Comment 2
          </Typography>
          <Typography paragraph>
          (username) Comment 3
          </Typography>
          <div className={styles.comment_container}>
          <input placeholder="Add comment" className={styles.input}></input>
          <button className={styles.button}>Send</button>
          </div>
        </CardContent>
      </Collapse>
    </Card>
    ))}
    </div>
  );
}
