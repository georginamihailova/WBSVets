const express = require('express');
const neo4j = require('neo4j-driver');
const cors = require('cors');

const app = express();
const port = 3000;

// Setup CORS (if you're running frontend and backend separately)
app.use(cors());

// Initialize Neo4j Driver with the new connection string for Neo4j 5.x
const driver = neo4j.driver('neo4j://localhost:7687', neo4j.auth.basic('nina', 'ninanina1'));
const session = driver.session({ database: 'neo4j' }); // Ensure we're connecting to the correct database

// Define a root route
app.get('/', (req, res) => {
    res.send('Welcome to the Veterinary Clinic API');
});

// Endpoint to fetch veterinary clinics and their information
app.get('/getVeterinaries', async (req, res) => {
    try {
        // Cypher query to get veterinary clinics, cities, and buildings
        const result = await session.run(`
            MATCH (c:City)-[:HASVETERINARY]->(v:Veterinary)-[:is_Of_Type]->(b:Building)
            RETURN v, c, b
        `);

        // Process the results and send them as JSON
        const veterinaryData = result.records.map(record => {
            const veterinary = record.get('v').properties;
            const city = record.get('c').properties;
            const building = record.get('b').properties;

            return {
                veterinary: {
                    name: veterinary.name,
                    legalEntity: veterinary.legalEntity,
                    address: veterinary.address,
                    dateEstablished: veterinary.dateEstablished,
                    location: city.name,
                    longitude: veterinary.longitude,
                    latitude: veterinary.latitude
                },
                buildingType: building.type
            };
        });

        res.json(veterinaryData);
    } catch (error) {
        console.error('Error fetching veterinary data:', error);
        res.status(500).send('Error fetching veterinary data');
    }
});

// Start the server
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
