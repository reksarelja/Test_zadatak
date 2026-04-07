import {useEffect, useState} from 'react'
import './App.css'
function App() {

    const [job_name, setJobName] = useState('');
    const [job_dateOfBirth, setJobDateOfBirth] = useState('');
    const [job_contact, setJobContact] = useState('');
    const [job_email, setJobEmail] = useState('');

    const [job_message, setJobMessage] = useState('');

    const handleSubmitJob = async (e) => {
        e.preventDefault();

        const requestBody = {
            name: job_name,
            dateOfBirth: job_dateOfBirth,
            contact: job_contact,
            email: job_email,
        };


        try {
            const response = await fetch('http://localhost:8080/Hr/rest_controller/save_candidate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestBody)
            });

            const data = await response.text();
            setJobMessage(data);
        } catch (error) {
            console.error('Error:', error);
            setJobMessage('Registration failed');
        }
    };

    const [skill_name, setSkillName] = useState('');

    const [skill_message, setSkillMessage] = useState('');

    const handleSubmitSkill = async (e) => {
        e.preventDefault();

        const requestBody = {
            name: skill_name,
        };

        try {
            const response = await fetch('http://localhost:8080/Hr/rest_controller/save_skill', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestBody)
            });

            const data = await response.text();
            setSkillMessage(data);
        } catch (error) {
            console.error('Error:', error);
            setSkillMessage('Registration failed');
        }
    };

    const [job_candidate, setJobCandidate] = useState({
            jobCandidateId: 0,
            jobCandidateName: '',
            jobCandidateDateOfBirth: '',
            jobCandidateContactNumber: '',
            jobCandidateEMail: '',
        }
    );

    const [skill, setSkill] = useState({
        skillId: 0,
        skillName: '',
    });
    const [job2candidate, setJob2candidate] = useState({
        job_candidate: {
            jobCandidateId: 0,
            jobCandidateName: '',
            jobCandidateDateOfBirth: '',
            jobCandidateContactNumber: '',
            jobCandidateEMail: '',
        },
        skill: {
            skillId: 0,
            skillName: '',
        }
    })
    const [j2s_message, setJob2candidateMessage] = useState('')

    const handleSubmitSkill2Job = async (e) => {
        e.preventDefault();

        const requestBody = {
            jobCandidate: job_candidate,
            skill: skill,
        };

        try {
            const response = await fetch('http://localhost:8080/Hr/rest_controller/give_candidate_skill', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestBody)
            });

            const data = await response.text();
            setJob2candidateMessage(data);
        } catch (error) {
            console.error('Error:', error);
            setJob2candidateMessage('Registration failed');
        }
    };

    const [byName, setByName] = useState('');
    const [byNameResults, setByNameResults] = useState([]);

    useEffect(() => {
        if (byName.length > 0) {
            fetch(`http://localhost:8080/Hr/rest_controller/search_candidate_name/${byName}`)
                .then(response => response.json())
                .then(data => setByNameResults(data))
                .catch(err => console.error("Search error:", err));
        }
        console.log(byNameResults);
    }, [byName]);

    const [skillId, setSkillId] = useState(0);
    const [bySkill, setBySkill] = useState([]);


    const addSkill = (e) => {
        e.preventDefault();
        if(skillId !== 0) {
            setBySkill([...bySkill, skillId]);
            setSkillId(0);
        }
    };

    const [bySkillMessage, setBySkillMessage] = useState([]);

    useEffect(() => {

        fetch(`http://localhost:8080/Hr/rest_controller/search_candidate_skill/${bySkill}`)
            .then(response => response.json())
            .then(data => setBySkillMessage(data))
            .catch(err => console.error("Search error:", err));

        console.log(bySkillMessage);
    }, [bySkill]);

    const [removeCandidateId, setRemoveCandidateId] = useState(0);
    const [removeCandidateMessage, setRemoveCandidateMessage] = useState('');

    const removeCandidate = async (e) => {
        e.preventDefault()
        try{
            const response = await fetch(`http://localhost:8080/Hr/rest_controller/remove_candidate/${removeCandidateId}`, {
                method: 'DELETE',
            });
            const data = await response.text();
            setRemoveCandidateMessage(data);
        } catch (error) {
            console.error('Error:', error);
            setRemoveCandidateMessage('Deletion failed');
        }
    }
    const [removeJ2SCandidate, setRemoveJ2SCandidate] = useState(0);
    const [removeJ2SSkill, setRemoveJ2SSkill] = useState(0);

    const[removeJ2SMessage, setRemoveJ2SMessage] = useState('');

    const removeSkillCandidate = async (e) => {
        e.preventDefault()
        try{
            const response = await fetch(`http://localhost:8080/Hr/rest_controller/remove_skill_from_candidate/${removeJ2SCandidate}/${removeJ2SSkill}`, {
                method: 'DELETE',
            });
            const data = await response.text();
            setRemoveJ2SMessage(data);
        } catch (error) {
            console.error('Error:', error);
            setRemoveJ2SMessage('Deletion failed');
        }
    }

    return (
        <>
            <label htmlFor="save_job">Save candidate:</label>
            <form onSubmit={handleSubmitJob} id="save_job">
                <input
                    type="text"
                    value={job_name}
                    onChange={(e) => setJobName(e.target.value)}
                    placeholder="Name"
                    required
                />
                <input
                    type="email"
                    value={job_email}
                    onChange={(e) => setJobEmail(e.target.value)}
                    placeholder="Email"
                    required
                />
                <input
                    type="date"
                    value={job_dateOfBirth}
                    onChange={(e) => setJobDateOfBirth(e.target.value)}
                    placeholder="DateOfBirth"
                    required
                />
                <input
                    type="text"
                    value={job_contact}
                    onChange={(e) => setJobContact(e.target.value)}
                    placeholder="Contact"
                    required
                />
                <button type="submit">Register</button>
                <p>{job_message}</p>
            </form>
            <label htmlFor="save_skill">Save skill:</label>
            <form onSubmit={handleSubmitSkill} id="save_skill">
                <input
                    type="text"
                    value={skill_name}
                    onChange={(e) => setSkillName(e.target.value)}
                    placeholder="Name"
                    required
                />
                <button type="submit">Register</button>
                <p>{skill_message}</p>
            </form>
            <label htmlFor="save_j2s">Save j2s:</label>
            <form onSubmit={handleSubmitSkill2Job} id="save_j2s">
                <label htmlFor="id_of_candidate">Id kandidata:</label>
                <input
                    id="id_of_candidate"
                    type="number"
                    value={job_candidate.jobCandidateId}
                    onChange={e => setJobCandidate({...job_candidate, jobCandidateId: e.target.valueAsNumber})}
                    required
                />
                <input
                    type="text"
                    value={job_candidate.jobCandidateName}
                    onChange={e => setJobCandidate({...job_candidate, jobCandidateName: e.target.value})}
                    placeholder="Candidate Name"
                    required
                />
                <input
                    type="text"
                    value={job_candidate.jobCandidateContactNumber}
                    onChange={e => setJobCandidate({...job_candidate, jobCandidateContactNumber: e.target.value})}
                    placeholder="Contact Number"
                    required
                />
                <input
                    type="email"
                    value={job_candidate.jobCandidateEMail}
                    onChange={e => setJobCandidate({...job_candidate, jobCandidateEMail: e.target.value})}
                    placeholder="Email"
                    required
                />
                <input
                    type="date"
                    value={job_candidate.jobCandidateDateOfBirth}
                    onChange={e => setJobCandidate({...job_candidate, jobCandidateDateOfBirth: e.target.value})}
                    placeholder="Date of Birth"
                    required
                />
                <label htmlFor="id_of_skill">Id vestine:</label>
                <input
                    id="id_of_skill"
                    type="number"
                    value={skill.skillId}
                    onChange={e => setSkill({...skill, skillId: e.target.valueAsNumber})}
                    required
                />
                <input
                    type="text"
                    value={skill.skillName}
                    onChange={e => setSkill({...skill, skillName: e.target.value})}
                    placeholder="Skill Name"
                    required
                />
                <button type="submit">Register</button>
                <p>{j2s_message}</p>
            </form>
            <label htmlFor="search_by_name">Search candidates by name:</label>
            <form id="search_by_name">
                <input
                    type="text"
                    value={byName}
                    onChange={(e) => setByName(e.target.value)}
                />
                {byNameResults.length > 0 ?
                    (<ul>
                        {byNameResults
                            .map(item => (
                                <li key={item.jobCandidateId}>{item.jobCandidateId}:
                                    {item.jobCandidateName},
                                    {item.jobCandidateDateOfBirth},
                                    {item.jobCandidateContactNumber},
                                    {item.jobCandidateEMail};
                                </li>
                            ))}
                    </ul>) : (
                        <p>No items...</p>
                    )
                }
            </form>

            <label htmlFor="search_by_skill">Search candidates by skill:</label>
            <form id="search_by_skill" onSubmit={addSkill}>
                <input
                    type="number"
                    value={skillId}
                    onChange={(e) => setSkillId(e.target.valueAsNumber)}
                />
                <button type="submit">Add skill</button>
            </form>
            <ul>
                {bySkill.map((item, index) => (
                    <li key={index}>{item}</li>
                ))}
            </ul>
            {bySkillMessage.length > 0 ?
                (<ul>
                    {bySkillMessage
                        .map(item => (
                            <li key={item.jobCandidateId}>{item.jobCandidateId}:
                                {item.jobCandidateName},
                                {item.jobCandidateDateOfBirth},
                                {item.jobCandidateContactNumber},
                                {item.jobCandidateEMail};
                            </li>
                        ))}
                </ul>) : (
                    <p>No items...</p>
                )
            }
            <label htmlFor="delete_by_id">Delete candidates by id:</label>
            <form id="delete_by_id" onSubmit={removeCandidate}>
                <input
                    type="number"
                    value={removeCandidateId}
                    onChange={(e) => setRemoveCandidateId(e.target.valueAsNumber)}
                    required
                />
                <button type="submit">Delete</button>
                <p>{removeCandidateMessage}</p>
            </form>
            <label htmlFor="delete_skill">Delete candidates by id:</label>
            <form id="delete_skill" onSubmit={removeSkillCandidate}>
                <label htmlFor="candidate_id"> Kandidat: </label>
                <input
                    id="candidate_id"
                    type="number"
                    value={removeJ2SCandidate}
                    onChange={(e) => setRemoveJ2SCandidate(e.target.valueAsNumber)}
                    required
                />
                <label htmlFor="skill_id"> Vestina: </label>
                <input
                    id="skill_id"
                    type="number"
                    value={removeJ2SSkill}
                    onChange={(e) => setRemoveJ2SSkill(e.target.valueAsNumber)}
                    required
                />
                <button type="submit">Delete</button>
                <p>{removeJ2SMessage}</p>
            </form>

        </>
    );

}

export default App