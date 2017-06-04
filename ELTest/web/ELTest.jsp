
Normal:<br>
<p>
${person.name} 's dog ${person.dog.name} 's toys are: ${person.dog.toys[0].name}, ${person.dog.toys[1].name}, and a 
${person.dog.toys[2].name}
</p>
Custom:<br>
<p>
${person["name"]} 's dog ${person["dog"].name} 's toys are: ${person["dog"].toys["0"].name}, ${person["dog"].toys["1"].name}, and a
${person["dog"].toys["2"].name}
</p>
