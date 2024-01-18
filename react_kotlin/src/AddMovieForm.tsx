import React, {useState} from "react";
import "./AddMovieForm.css"

interface AddMovieFormProps {
    setChange: React.Dispatch<React.SetStateAction<number>>;
}

function AddMovieForm({setChange}: AddMovieFormProps) {
    const [formData, setFormData] = useState({
        name: '',
        author: '',
        mainActor: '',
        ratingCSFD: 0,
        myRating: 0,
        year: 0,
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setFormData(prevData => ({
            ...prevData,
            [name]: value,
        }));

    };

    const submit = () => {

        fetch("http://localhost:8080/", {
            method: 'POST',
            headers: {
                'content-type': 'application/json;charset=UTF-8',
            },
            body: JSON.stringify(formData),
        }).then(response => {
            setChange(response.status)
            formData.name = ""
            formData.author = ""
            formData.mainActor = ""
            formData.ratingCSFD = 0
            formData.myRating = 0
            formData.year = 0
        })
    }

    return (
        <div id={"movieFormMainBox"}>

            <label>Name:<input type="text" name="name" value={formData.name} onChange={handleChange}/></label>

            <label>Author:<input type="text" name="author" value={formData.author} onChange={handleChange}/></label>

            <label>Main Actor:<input type="text" name="mainActor" value={formData.mainActor}
                                     onChange={handleChange}/></label>

            <label>Rating (CSFD):<input type="number" name="ratingCSFD" value={formData.ratingCSFD}
                                        onChange={handleChange}/></label>

            <label>My Rating:<input type="number" name="myRating" value={formData.myRating}
                                    onChange={handleChange}/></label>

            <label>Year:<input type="number" name="year" value={formData.year} onChange={handleChange}/></label>

            <button onClick={submit}>Submit</button>
        </div>
    )
}

export default AddMovieForm