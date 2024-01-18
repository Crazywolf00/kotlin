import React, {useEffect, useState} from 'react';
import './MoviePresent.css';
import AddMovieForm from "./AddMovieForm";

interface Movie {
    id: number;
    name: string;
    author: string;
    mainActor: string;
    ratingCSFD: number;
    myRating: number;
    year: number;
}

function MoviesPresent() {
    const [data, setData] = useState<Movie[] | null>(null);
    const [change, setChange] = useState(0);


    useEffect(() => {
        fetch("http://localhost:8080/")
            .then(response => response.json())
            .then(data => {
                setData(data)
                if (change > 10) {
                    setChange(0)
                }
            })

    }, [change])

    const deleteMovie = (id: number) =>
        fetch(`http://localhost:8080/${id}`, {
            method: "DELETE"
        })
            .then(response => setChange(response.status))

    const sortByRatingCSFD = () => {
        setData((prevData) => [...(prevData ?? [])].sort((a, b) => b.ratingCSFD - a.ratingCSFD));
        setChange(5)
    }
    const sortByMyRating = () => {
        setData((prevData) => [...(prevData ?? [])].sort((a, b) => b.myRating - a.myRating));
        setChange(5)
    }
    const sortByYear = () => {
        setData((prevData) => [...(prevData ?? [])].sort((a, b) => b.year - a.year));
        setChange(5)
    }
    const sortByAuthor = () => {
        if (data && Array.isArray(data)) {
            setData((prevData) => [...(prevData ?? [])].sort((a, b) => b.author.localeCompare(a.author)));
            setChange(5);
        }
    }
    const sortByMainActor = () => {
        if (data && Array.isArray(data)) {
            setData((prevData) => [...(prevData ?? [])].sort((a, b) => a.mainActor.localeCompare(b.mainActor, 'en', { sensitivity: 'base' })));
            setChange(5);
        }
    }
    return (
        <div>
            <AddMovieForm setChange={setChange}/>
            <h2>Movie List</h2>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th onClick={sortByAuthor}>Author</th>
                    <th onClick={sortByMainActor}>MainActor</th>
                    <th onClick={sortByRatingCSFD}>RatingCSFD</th>
                    <th onClick={sortByMyRating}>MyRating</th>
                    <th onClick={sortByYear}>Year</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {data?.map(movie => (
                    <tr key={movie.id}>
                        <td>{movie.name}</td>
                        <td>{movie.author}</td>
                        <td>{movie.mainActor}</td>
                        <td>{movie.ratingCSFD}</td>
                        <td>{movie.myRating}</td>
                        <td>{movie.year}</td>
                        <td>
                            <button onClick={() => deleteMovie(movie.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default MoviesPresent