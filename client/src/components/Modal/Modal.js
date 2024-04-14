import {useState} from "react";
import {post} from "@/service/userService";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import styles from "./modal.module.css";
import Button from "../Button/Button";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

export default function BasicModal({
  handleOpen = () => {},
  handleClose = () => {},
  open,
}) {
    const [file, setFile] = useState();
    const [caption, setCaption] = useState(null);
    const formSubmit= async (event)=>{
        event.preventDefault();
        console.log("file:: ",file);
        const formData = new FormData();
        formData.append('file',file);
        formData.append('fileName',file.name);
        formData.append('text',caption);
        try {
          const res = await post(formData);
          console.log("uploading photo: ",res.data);
        } catch (error) {
          console.log("Error while uploading photo: ",error)
        }
    }
  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={{...style,display:'flex',flexDirection:'column',gap:'1.3rem'}}>
        <Typography
          id="modal-modal-title"
          variant="h6"
          component="h2"
          style={{ textAlign: "center" }}
        >
          Create a post
        </Typography>
        <form className={styles.form} onSubmit={formSubmit}>
          <label className={styles.flex}>
            Select a photo
            <input type="file" name="file" required onChange={e=>setFile(e.target.files[0])}/>
          </label>
          <label className={styles.flex}>
            Caption:
            <textarea name="caption" onChange={e=>setCaption(e.target.value)}/>
          </label>
        <Button type="submit">Post</Button>
        </form>
      </Box>
    </Modal>
  );
}
